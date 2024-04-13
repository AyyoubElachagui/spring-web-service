package com.example.factureservice.controller;

import com.example.factureservice.client.ClientRest;
import com.example.factureservice.client.ProductRest;
import com.example.factureservice.entity.Facture;
import com.example.factureservice.entity.FactureProducts;
import com.example.factureservice.models.Client;
import com.example.factureservice.models.Product;
import com.example.factureservice.repository.FactureProductsRepository;
import com.example.factureservice.repository.FactureRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class FactureController {
    private FactureRepository factureRepository;
    private FactureProductsRepository factureProductsRepository;
    private ProductRest productRest;
    private ClientRest clientRest;

    @GetMapping("/factures")
    public List<Facture> getAllFacture(){
//        return clientRest.getAllClients();
        List<Facture> factures = factureRepository.findAll();
        if (!factures.isEmpty()) {
            factures.forEach(facture -> {
                facture.setClient(clientRest.getClientById(facture.getClientId()));
                facture.getOrders().forEach(product -> {
                    product.setProduct(productRest.getProductById(product.getProductId()));
                });
            });
            return factures;
        };
        return factureRepository.findAll();
    }

    @GetMapping("/factures/{id}")
    public Facture getFactureById(@PathVariable UUID id){
        Facture facture =  factureRepository.findById(id).get();
        facture.setClient(clientRest.getClientById(facture.getClientId()));
        facture.getOrders().forEach(order -> order.setProduct(productRest.getProductById(order.getProductId())));
        return facture;
    }

    @GetMapping("/factures/client/{id}")
    public List<Facture> getFactureByClientId(@PathVariable UUID id){
        List<Facture> factures = factureRepository.findByClientId(id);
        if (!factures.isEmpty()) {
            for (Facture facture : factures) {
                facture.setClient(clientRest.getClientById(facture.getClientId()));
                for(FactureProducts f : facture.getOrders()){
                    Product p = productRest.getProductById(f.getProductId());
                    f.setProduct(p);
                }
            }
            return factures;
        };
        return factureRepository.findByClientId(id);
    }

    @PostMapping("/factures/store")
    public Facture getFactureById(@RequestBody Facture facture){
        return factureRepository.save(facture);
    }

}
