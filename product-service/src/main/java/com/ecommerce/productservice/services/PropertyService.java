package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Property;
import com.ecommerce.productservice.models.Subcategory;
import com.ecommerce.productservice.repositories.PropertyRepository;
import com.ecommerce.productservice.repositories.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import utilities.dtos.ProviderResponse;
import utilities.models.ResponseMessage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final StorageService storage;
    private final Path rootLocation = Paths.get("upload");

    private final WebClient.Builder webClientBuilder;

    public List<Property> getAll(){
        return propertyRepository.findAll() ;
    }

    public Property getOneProperty( Long id){
        return propertyRepository.findById(id).orElse(null);
    }

    public void deleteProperty (Long id) {
        propertyRepository.deleteById(id);
    }

    public Property createProperty (Property p){
        return propertyRepository.save(p);
    }


    public Property updateProperty (Property i, Long id){
        Property i1 = propertyRepository.findById(id).orElse(null);
        if (i1 != null) {
            i.setId(id);
            return propertyRepository.saveAndFlush(i);
        } else {
            throw new RuntimeException("fail");
        }

    }


    public ResponseEntity<ResponseMessage> uploadFiles (MultipartFile[]files, Property i, Long
            id_Subcategory, Long id_provider){
        String message = "";
        try {
            ArrayList<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    String fileName = Integer.toString(new Random().nextInt(1000000));
                    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                            file.getOriginalFilename().length());
                    String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                    String original = name + fileName + ext;
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
                    fileNames.add(original);
                    i.setImage(fileName);
                } catch (Exception e) {
                    throw new RuntimeException("fail file problem backend");
                }
            });

            ProviderResponse pr = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/provider/" + id_provider)
                    .retrieve()
                    .bodyToMono(ProviderResponse.class)
                    .block();
            i.setProviderId(pr.getId());
            Subcategory sub = subcategoryRepository.findById(id_Subcategory).orElse(null);
            i.setSubcategory(sub);

            propertyRepository.save(i);
            message = "Uploaded the file successufully" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "fail to upload";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    public Property saveProperty (MultipartFile file, Property immobilier, Long id_subcategory, Long
            id_provider){
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000));
            String ext = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf('.')
            );
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = name + fileName + ext;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
            ProviderResponse pr = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/provider/" + id_provider)
                    .retrieve()
                    .bodyToMono(ProviderResponse.class)
                    .block();
            immobilier.setProviderId(pr.getId());
            Subcategory sub = subcategoryRepository.findById(id_subcategory).orElse(null);
            immobilier.setSubcategory(sub);
            immobilier.setImage(original);
            return propertyRepository.save(immobilier);

        } catch (Exception e) {
            throw new RuntimeException("fail file problem backend");
        }
    }

    public ResponseEntity<Resource> getFile (String filename){
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
