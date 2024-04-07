package com.example.factureservice.models;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    private UUID id;

    private String type;
    private String reference;
    private String description;
    private double price;
}
