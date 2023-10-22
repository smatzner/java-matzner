package com.example.entitiesaufgabe.repositories;

import com.example.entitiesaufgabe.entities.keys.Article_Purchase_PK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Article_PurchaseRepository extends JpaRepository<com.example.entitiesaufgabe.entities.Article_Purchase, Article_Purchase_PK> {
}
