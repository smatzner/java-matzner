package com.example.entitiesaufgabe.repositories;

import com.example.entitiesaufgabe.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
