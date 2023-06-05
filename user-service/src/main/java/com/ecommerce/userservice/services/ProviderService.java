package com.ecommerce.userservice.services;

import com.ecommerce.userservice.models.Provider;
import com.ecommerce.userservice.repositories.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    public List<Provider> getAll() {
        return providerRepository.findAll();
    }

    public Provider getOneProvider(Long id) {
        return providerRepository.findById(id).orElse(null);
    }

    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }

    public Provider updateProvider(Provider provider, Long id) {
        Provider provider1 = providerRepository.findById(id).orElse(null);
        if (provider1 != null) {
            provider.setId(id);
            return providerRepository.saveAndFlush(provider);
        } else {
            throw new RuntimeException("fail");
        }

    }
}
