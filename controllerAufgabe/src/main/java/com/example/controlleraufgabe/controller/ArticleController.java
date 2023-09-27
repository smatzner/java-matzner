package com.example.controlleraufgabe.controller;

import com.example.controlleraufgabe.ControllerAufgabeApplication;
import com.example.controlleraufgabe.dto.ArticleDTO;
import com.example.controlleraufgabe.entity.Article;
import org.springframework.web.bind.annotation.*;

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
}
