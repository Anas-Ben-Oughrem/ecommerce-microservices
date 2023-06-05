package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.models.Subcategory;
import com.ecommerce.productservice.repositories.ProductRepository;
import com.ecommerce.productservice.repositories.SubcategoryRepository;
import com.ecommerce.productservice.services.ProductService;
import com.ecommerce.productservice.services.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import utilities.dtos.ProviderResponse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final WebClient.Builder webClientBuilder;

    private final StorageService storage;

    private final Path rootLocation = Paths.get("upload-dir");


    @Operation(summary = "Find All Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)})

    @GetMapping("/all")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER') ")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "product not found",
                    content = @Content)})

    @GetMapping("/getOneProduct/{id}")
    public Product getOneProduct(@Parameter(description = "id of product to be searched") @PathVariable Long id) {
        return productService.getOneProductService(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        return productService.createProductService(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        return productService.deleteproductService(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product p, @PathVariable Long id) {
        return productService.updateproductService(p, id);
    }



    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFileService(@PathVariable String filename) {
        return productService.getFileService(filename);
    }


    @GetMapping("/Product")
    public ResponseEntity<List<String>> getProduct() {
        List<String> Product = Arrays.asList("Option 1", "Option 2", "Option 3");
        return ResponseEntity.ok(Product);
    }

    //les méthodes save avec les relations
    @PostMapping("/save/{idprovider}/{idsubcategory}")
    public Product saveproduct(@RequestBody Product p, @PathVariable Long idprovider, @PathVariable Long idsubcategory) {
        ProviderResponse provider = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/providers/getOneProvider/" + idprovider)
                .retrieve()
                .bodyToMono(ProviderResponse.class)
                .block();
        p.setProviderId(provider.getId());
        Subcategory sub = subcategoryRepository.findById(idsubcategory).orElse(null);
        p.setSubcategory(sub);
        return productRepository.save(p);
    }

    //update avec les relations
    @PutMapping("/update1/{Id}")
    public Product updateProduct1(@RequestBody Product p, @PathVariable Long Id) {
        Product p1 = productRepository.findById(Id).orElse(null);

        if (p1 != null) {
            p.setId(Id);

            p.setSubcategory(p1.getSubcategory());
            p.setProviderId(p1.getProviderId());

            return productRepository.saveAndFlush(p);
        } else {
            throw new RuntimeException("FAIL!");
        }

    }

//pagination & pipe

    @GetMapping("/chercher")
    public List<Product> findProductByName(String name) {

        return productRepository.chercher("%" + name + "%");
    }

    @GetMapping("/getbypage")
    public Page<Product> getProducts(int page, int size) {

        return productRepository.findbypage(PageRequest.of(page, size));
    }

    //add product with image


    @RequestMapping(
            path = "/save2",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product saveproduct(@RequestParam(value = "file") MultipartFile file, Product product) {
        storage.store(file);
        product.setImage(file.getOriginalFilename());
        return productRepository.save(product);
    }

    @RequestMapping(
            path = "/save1/{idprovider}/{idsubcategory}",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product saveproduct2(@RequestParam("file") MultipartFile file, Product p, @PathVariable Long idprovider, @PathVariable Long idsubcategory) {
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = name + fileName + ext;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
            p.setImage(original);

            ProviderResponse provider = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/providers/getOneProvider/" + idprovider)
                    .retrieve()
                    .bodyToMono(ProviderResponse.class)
                    .block();

            p.setProviderId(provider.getId());
            Subcategory sub = subcategoryRepository.findById(idsubcategory).orElse(null);
            p.setSubcategory(sub);
        } catch (Exception e) {
            throw new RuntimeException("FAIL File Prolem BackEnd !");
        }

        return productRepository.save(p);
    }


// add product with gallery d'images

    @PutMapping("/updateqte/{id}")
    public Product updateProductQte(String qte, @PathVariable Long id) {
        return productService.updateProductqteService(qte, id);
    }

}
