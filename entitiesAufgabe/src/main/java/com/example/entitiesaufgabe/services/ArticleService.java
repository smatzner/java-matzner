package com.example.entitiesaufgabe.services;

import com.example.entitiesaufgabe.EntitiesAufgabeApplication;
import com.example.entitiesaufgabe.dto.ArticleDTO;
import com.example.entitiesaufgabe.dto.ArticlePriceDTO;
import com.example.entitiesaufgabe.entities.Article;
import com.example.entitiesaufgabe.repositories.ArticleRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public void saveArticle(ArticleDTO articleDTO) {
        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
            if (article.getArticleName().equalsIgnoreCase(articleDTO.getArticleName())) {
                throw new EntityExistsException("Der Artikel mit der Bezeichnung " + articleDTO.getArticleName() + " ist bereits in der Datenbank angelegt.");
            }
        });

        Article article = Article.builder()
                .articleName(articleDTO.getArticleName())
                .articlePrice(articleDTO.getArticlePrice())
                .build();


        articleRepository.save(article);
    }

    public Set<ArticleDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();
        Set<ArticleDTO> articleDTOS = new HashSet<>();

        for (Article article : articles) {
            articleDTOS.add(new ArticleDTO(
                    article.getArticleName(),
                    article.getArticlePrice()
            ));
        }

        return articleDTOS;
    }

    public ArticleDTO getArticleById(int articleId) {
        if (articleRepository.findById(articleId).isEmpty()) {
            throw new NoSuchElementException("Keinen Artikel mit der Id " + articleId + " gefunden!");
        }

        Article article = articleRepository.findById(articleId).get();

        ArticleDTO articleDTO = new ArticleDTO(
                article.getArticleName(),
                article.getArticlePrice()
        );

        return articleDTO;
    }

    public ArticleDTO updateArticlePrice(int articleId, ArticlePriceDTO articlePriceDTO) {
        if (articleRepository.findById(articleId).isEmpty()) {
            throw new NoSuchElementException("Kein Artikel mit der Id " + articleId + " gefunden!");
        }

        Article article = articleRepository.findById(articleId).get();
        article.setArticlePrice(articlePriceDTO.getArticlePrice());
        articleRepository.save(article);

        ArticleDTO articleDTO = new ArticleDTO(
                article.getArticleName(),
                article.getArticlePrice()
        );

        return articleDTO;
    }
}
