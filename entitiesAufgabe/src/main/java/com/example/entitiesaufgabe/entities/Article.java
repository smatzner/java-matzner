package com.example.entitiesaufgabe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int articleId;

    @Column(name = "title", nullable = false)
    private String articleName;

    @Column(name = "price")
    private double articlePrice;

    @OneToMany(mappedBy = "article")
    private List<Article_Purchase> articlePurchaseList;
}
