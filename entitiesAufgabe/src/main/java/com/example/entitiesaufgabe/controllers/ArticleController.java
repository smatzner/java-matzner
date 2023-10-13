package com.example.entitiesaufgabe.controllers;

import com.example.entitiesaufgabe.dto.ArticleDTO;
import com.example.entitiesaufgabe.dto.ArticlePriceDTO;
import com.example.entitiesaufgabe.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<String> saveArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.saveArticle(articleDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Artikel hinzugefügt");
    }

    @GetMapping
    public ResponseEntity<Set<ArticleDTO>> getArticles() {
        Set<ArticleDTO> articleDTOS = articleService.getArticles();

        return ResponseEntity.status(HttpStatus.OK).body(articleDTOS);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable int articleId) {
        ArticleDTO articleDTO = articleService.getArticleById(articleId);

        if (articleDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artikel nicht gefunden");

        return ResponseEntity.status(HttpStatus.OK).body(articleDTO);

    }

    @PutMapping("{articleId}")
    public ResponseEntity<?> updateArticlePrice(@PathVariable int articleId, @RequestBody ArticlePriceDTO articlePriceDTO) {
        ArticleDTO articleDTO = articleService.updateArticlePrice(articleId, articlePriceDTO);

        if (articleDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artikel nicht gefunden");

        return ResponseEntity.status(HttpStatus.OK).body(articleDTO);
    }
}
