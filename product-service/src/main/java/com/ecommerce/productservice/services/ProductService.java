package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.models.Subcategory;
import com.ecommerce.productservice.repositories.ProductRepository;
import com.ecommerce.productservice.repositories.SubcategoryRepository;
import io.swagger.v3.oas.annotations.Parameter;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final WebClient.Builder webClientBuilder;
    private final SubcategoryRepository subcategoryRepository;
    private final StorageService storage;
    private final Path rootLocation = Paths.get("upload");


    public Product getOneProductService(@Parameter(description = "id of product to be searched")  Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("FAIL!"));
    }


    public List<Product> getAll(){
        return productRepository.findAll() ;
    }



    public ResponseEntity deleteproductService (Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Product createProductService (Product p){
        return productRepository.save(p);
    }


    public Product updateproductService (Product p, Long id){
        Product p1 = productRepository.findById(id).orElse(null);
        if (p1 != null) {
            p.setId(id);
            return productRepository.saveAndFlush(p);
        } else {
            throw new RuntimeException("fail");
        }

    }


    public ResponseEntity<ResponseMessage> uploadFiles (MultipartFile[]files, Product p, Long id_subcategory, Long id_provider){
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
                    p.setImage(fileName);
                } catch (Exception e) {
                    throw new RuntimeException("fail file problem backend");
                }
            });

            ProviderResponse pr = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/providers/getone/" + id_provider)
                    .retrieve()
                    .bodyToMono(ProviderResponse.class)
                    .block();
            p.setProviderId(pr.getId());
            Subcategory sub = subcategoryRepository.findById(id_subcategory).orElse(null);
            p.setSubcategory(sub);

            productRepository.save(p);
            message = "Uploaded the file successufully" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "fail to upload";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    public Product saveproductService (MultipartFile file, Product product, Long id_subcategory, Long
            id_provider){
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                    file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = name + fileName + ext;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
            ProviderResponse fr = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/providers/getone/" + id_provider)
                    .retrieve()
                    .bodyToMono(ProviderResponse.class)
                    .block();
            product.setProviderId(fr.getId());
            Subcategory sub = subcategoryRepository.findById(id_subcategory).orElse(null);
            product.setSubcategory(sub);
            product.setImage(original);
            return productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("fail file problem backend");
        }
    }


    public ResponseEntity<Resource> getFileService (String filename){
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=\"" + file.getFilename() + "\"")
                .body(file);
    }



    public Product updateProductqteService(String qte,  Long id) {
        Product p1 = productRepository.findById(id).orElse(null);
        if (p1 != null) {
            p1.setId(id);
            p1.setQte(qte);
            return productRepository.saveAndFlush(p1);
        }
        else{
            throw new RuntimeException("FAIL!");
        }
    }
}
