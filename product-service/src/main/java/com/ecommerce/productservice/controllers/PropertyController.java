package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.models.Property;
import com.ecommerce.productservice.services.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utilities.models.ResponseMessage;

import java.util.List;

@RestController
@RequestMapping("/properties")
@CrossOrigin("*")
@RequiredArgsConstructor

public class PropertyController {

    private final PropertyService propertyService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Property> getAll(){
        return propertyService.getAll() ;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Property getOneProperty(@PathVariable Long id){
        return propertyService.getOneProperty(id);
    }


    @PostMapping
    public Property createProperty (@RequestBody Property i){
        return propertyService.createProperty(i);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProperty (@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }



    @PutMapping("/{id}")
    public Property updateProperty (@RequestBody Property i, @PathVariable Long id){
        return propertyService.updateProperty(i,id);
    }
    @PostMapping("/upload/{id_fournisseur}/{id_souscategorie}")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files, Property i,
                                                              @PathVariable Long id_souscategorie, @PathVariable Long id_fournisseur ) {
        return propertyService.uploadFiles(files,i,id_souscategorie,id_fournisseur);
    }

    @PostMapping("/{id_fournisseur}/{id_souscategorie}")
    public Property saveProperty(@RequestParam("file") MultipartFile file, Property immobilier,
                                   @PathVariable Long id_souscategorie, @PathVariable Long id_fournisseur ) {
        return propertyService.saveProperty(file,immobilier,id_souscategorie,id_fournisseur);
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource>getFileService(@PathVariable String filename){
        return propertyService.getFile(filename);
    }
}
