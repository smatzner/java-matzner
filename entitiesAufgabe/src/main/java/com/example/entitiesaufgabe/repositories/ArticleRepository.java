package com.example.entitiesaufgabe.repositories;

import com.example.entitiesaufgabe.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {}
