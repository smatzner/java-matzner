package com.example.serviceaufgabe.controller;

import com.example.serviceaufgabe.ServiceAufgabeApplication;
import com.example.serviceaufgabe.dto.ArticleDTO;
import com.example.serviceaufgabe.dto.ArticlePriceDTO;
import com.example.serviceaufgabe.entity.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @PostMapping
    public String saveArticle(@RequestBody ArticleDTO articleDTO){
        Article article = new Article(
                articleDTO.getArticleId(),
                articleDTO.getArticleName(),
                articleDTO.getArticlePrice()
        );

        ServiceAufgabeApplication.articles.add(article);

        return "Artikel erfolgreich angelegt";
    }

    @GetMapping
    public Set<Article> getArticle(){
        return ServiceAufgabeApplication.articles;
    }

    @GetMapping("{articleId}")
    public Optional<Article> getArticleById(@PathVariable int articleId){
        return ServiceAufgabeApplication.articles.stream()
                .filter(article -> article.getArticleId() == articleId)
                .findFirst();
    }

    @PutMapping("{articleId}")
    public ResponseEntity<?> updateArticlePrice(@PathVariable int articleId, @RequestBody ArticlePriceDTO articlePriceDTO) {
        for (Article article : ServiceAufgabeApplication.articles){
            if(article.getArticleId() == articleId){
                article.setArticlePrice(articlePriceDTO.getArticlePrice());
                return ResponseEntity.ok(article);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artikel nicht gefunden");
    }
}
