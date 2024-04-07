package com.example.factureservice.client;

import com.example.factureservice.models.Client;
import com.example.factureservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("CLIENT-SERVICE")
public interface ClientRest {

    @GetMapping("/clients")
    List<Client> getAllClients();

    @GetMapping("/clients/{id}")
    Client getClientById(@PathVariable UUID id);
}
