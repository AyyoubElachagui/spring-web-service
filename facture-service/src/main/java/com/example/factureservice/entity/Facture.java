package com.example.factureservice.entity;

import com.example.factureservice.models.Client;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Transient
    private Client client;

    private UUID clientId;

    private double amount;

    @ManyToMany
    @JoinTable(
            name = "facture_product",
            joinColumns = @JoinColumn(name = "facture_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<FactureProducts> orders;

}
