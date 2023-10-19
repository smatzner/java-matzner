package com.example.entitiesaufgabe.controllers;

import com.example.entitiesaufgabe.dto.ArticleDTO;
import com.example.entitiesaufgabe.dto.ArticlePriceDTO;
import com.example.entitiesaufgabe.services.ArticleService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<String> saveArticle(@RequestBody ArticleDTO articleDTO) {

        try {
            articleService.saveArticle(articleDTO);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Artikel hinzugefügt");
    }

    @GetMapping
    public ResponseEntity<Set<ArticleDTO>> getArticles() {
        Set<ArticleDTO> articleDTOS = articleService.getArticles();

        return ResponseEntity.status(HttpStatus.OK).body(articleDTOS);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable int articleId) {
        ArticleDTO articleDTO;

        try {
            articleDTO = articleService.getArticleById(articleId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(articleDTO);
    }

    @PutMapping("{articleId}")
    public ResponseEntity<?> updateArticlePrice(@PathVariable int articleId, @RequestBody ArticlePriceDTO articlePriceDTO) {
        ArticleDTO articleDTO;

        try {
            articleDTO = articleService.updateArticlePrice(articleId, articlePriceDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(articleDTO);
    }
}
