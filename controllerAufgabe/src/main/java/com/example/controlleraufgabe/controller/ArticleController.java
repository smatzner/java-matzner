package com.example.controlleraufgabe.controller;

import com.example.controlleraufgabe.ControllerAufgabeApplication;
import com.example.controlleraufgabe.dto.ArticleDTO;
import com.example.controlleraufgabe.dto.ArticlePriceDTO;
import com.example.controlleraufgabe.entity.Article;
import org.apache.coyote.Response;
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

        ControllerAufgabeApplication.articles.add(article);

        return "Artikel erfolgreich angelegt";
    }

    @GetMapping
    public Set<Article> getArticle(){
        return ControllerAufgabeApplication.articles;
    }

    @GetMapping("{articleId}")
    public Optional<Article> getArticleById(@PathVariable int articleId){
        return ControllerAufgabeApplication.articles.stream()
                .filter(article -> article.getArticleId() == articleId)
                .findFirst();
    }

    @PutMapping("{articleId}")
    public ResponseEntity<?> updateArticlePrice(@PathVariable int articleId, @RequestBody ArticlePriceDTO articlePriceDTO) {
        for (Article article : ControllerAufgabeApplication.articles){
            if(article.getArticleId() == articleId){
                article.setArticlePrice(articlePriceDTO.getArticlePrice());
                return ResponseEntity.ok(article);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artikel nicht gefunden");
    }
}
