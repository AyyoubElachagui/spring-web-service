package com.example.factureservice.repository;

import com.example.factureservice.entity.FactureProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FactureProductsRepository extends JpaRepository<FactureProducts, UUID> {
}
