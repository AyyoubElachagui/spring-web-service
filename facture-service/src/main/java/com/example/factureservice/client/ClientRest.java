package com.example.factureservice.client;

import com.example.factureservice.models.Client;
import com.example.factureservice.models.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("CLIENT-SERVICE")
public interface ClientRest {

    @GetMapping("/api/clients")
    @CircuitBreaker(name = "clientService", fallbackMethod = "getAllClientsDefault")
    List<Client> getAllClients();

    @GetMapping("/api/clients/{id}")
    @CircuitBreaker(name = "clientService", fallbackMethod = "getClientByIdDefault")
    Client getClientById(@PathVariable UUID id);

    default List<Client> getAllClientsDefault(Exception exception) {
        return List.of();
    }

    default Client getClientByIdDefault(UUID id, Exception exception){
        Client client = new Client();
        client.setId(id);
        client.setCity("default");
        client.setPhone("default");
        client.setAddress("default");
        client.setFirstName("default");
        client.setLastName("default");
        return client;
    }

}
