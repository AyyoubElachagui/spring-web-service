package com.example.factureservice.repository;

import com.example.factureservice.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FactureRepository extends JpaRepository<Facture, UUID> {

    List<Facture> findByClientId(UUID clientId);
}
