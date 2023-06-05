package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Subcategory;
import com.ecommerce.productservice.models.Vehicle;
import com.ecommerce.productservice.repositories.SubcategoryRepository;
import com.ecommerce.productservice.repositories.VehicleRepository;
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
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final StorageService storage;

    private final WebClient.Builder webClientBuilder;

    private final Path rootLocation = Paths.get("upload");

    public List<Vehicle> getAll(){
        return vehicleRepository.findAll() ;
    }

    public Vehicle getOneVehicle( Long id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public void deleteVehicle (Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle createVehicle (Vehicle v){
        return vehicleRepository.save(v);
    }


    public Vehicle updateVehicle (Vehicle v, Long id){
        Vehicle v1 = vehicleRepository.findById(id).orElse(null);
        if (v1 != null) {
            v.setId(id);
            return vehicleRepository.saveAndFlush(v);
        } else {
            throw new RuntimeException("fail");
        }

    }

    public ResponseEntity<ResponseMessage> uploadFiles (MultipartFile[]files, Vehicle v, Long
            id_subcategory, Long id_provider){
        String message = "";
        try {
            ArrayList<String> fileNames = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                try {
                    String fileName = Integer.toString(new Random().nextInt(1000000));
                    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                            file.getOriginalFilename().length());
                    String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                    String original = name + fileName + ext;
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
                    fileNames.add(original);
                    v.setImage(fileName);
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
            v.setProviderId(id_provider);
            Subcategory sub = subcategoryRepository.findById(id_subcategory).orElse(null);
            v.setSubcategory(sub);

            vehicleRepository.save(v);
            message = "Uploaded the file successufully" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "fail to upload";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    public Vehicle saveVehicle (MultipartFile file, Vehicle vehicule, Long id_subcategory, Long
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
            vehicule.setProviderId(pr.getId());
            Subcategory sub = subcategoryRepository.findById(id_subcategory).orElse(null);
            vehicule.setSubcategory(sub);
            vehicule.setImage(original);
            return vehicleRepository.save(vehicule);

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
