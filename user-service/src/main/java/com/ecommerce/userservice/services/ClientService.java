package com.ecommerce.userservice.services;

import com.ecommerce.userservice.models.Client;
import com.ecommerce.userservice.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.findAll() ;
    }

    public Client getOneClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient ( Client client){
        return clientRepository.save(client);
    }

    public void deleteClient (Long id) {
        clientRepository.deleteById(id);
    }

    public Client updateClient ( Client client,  Long id){
        Client c1 = clientRepository.findById(id).orElse(null);
        if (c1 !=null){
            client.setId(id);
            return clientRepository.saveAndFlush(client);
        }
        else{
            throw new RuntimeException("fail");
        }
    }
}
