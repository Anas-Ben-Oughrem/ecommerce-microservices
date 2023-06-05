package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.models.Vehicle;
import com.ecommerce.productservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utilities.models.ResponseMessage;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin("*")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService ;

    @GetMapping
    public List<Vehicle> getAll(){
        return vehicleService.getAll() ;
    }

    @GetMapping("/{id}")
    public Vehicle getOneVehicle(@PathVariable Long id){
        return vehicleService.getOneVehicle(id);
    }


    @PostMapping
    public Vehicle createVehicle (@RequestBody Vehicle vehicle){
        return vehicleService.createVehicle(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle (@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }



    @PutMapping("/{id}")
    public Vehicle updateVehicle (@RequestBody Vehicle vehicle, @PathVariable Long id){
        return vehicleService.updateVehicle(vehicle,id);
    }

    @PostMapping("/upload/{id_fournisseur}/{id_souscategorie}")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files, Vehicle v,
                                                              @PathVariable Long id_souscategorie, @PathVariable Long id_fournisseur ) {
        return vehicleService.uploadFiles(files,v,id_souscategorie,id_fournisseur);
    }

    @PostMapping("/{id_fournisseur}/{id_souscategorie}")
    public Vehicle saveVehicle(@RequestParam("file") MultipartFile file, Vehicle vehicle,
                                @PathVariable Long id_souscategorie, @PathVariable Long id_fournisseur ) {
        return vehicleService.saveVehicle(file,vehicle,id_souscategorie,id_fournisseur);
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource>getFileService(@PathVariable String filename){
        return vehicleService.getFile(filename);
    }
}