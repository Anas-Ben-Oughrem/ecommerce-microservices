package com.ecommerce.userservice.controllers;

import com.ecommerce.userservice.models.Provider;
import com.ecommerce.userservice.services.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public Provider createProvider (@RequestBody Provider provider){
        return providerService.createProvider(provider);
    }

    @GetMapping
    public List<Provider> getAllProviders(){
        return providerService.getAll();
    }

    @GetMapping("/{id}")
    public Provider getOneProvider(@PathVariable Long id){
        return providerService.getOneProvider(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProvider (@PathVariable Long id) {
        providerService.deleteProvider(id);
    }

    @PutMapping("/{id}")
    public Provider updateProvider (@RequestBody Provider provider, @PathVariable Long id) {
        return providerService.updateProvider(provider, id);
    }
}
