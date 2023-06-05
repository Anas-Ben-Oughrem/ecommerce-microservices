package com.ecommerce.userservice.controllers;

import com.ecommerce.userservice.models.Client;
import com.ecommerce.userservice.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Client createClient (Client client){
        return clientService.createClient(client);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getOneClient( @PathVariable Long id){
        return clientService.getOneClient(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient (@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @PutMapping("/{id}")
    public Client updateClient (@RequestBody Client client, @PathVariable Long id) {
        return clientService.updateClient(client, id);
    }
}
