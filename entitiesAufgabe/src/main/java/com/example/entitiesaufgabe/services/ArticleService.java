package com.example.entitiesaufgabe.services;

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
            if (article.getTitle().equalsIgnoreCase(articleDTO.getTitle())) {
                throw new EntityExistsException("Der Artikel mit der Bezeichnung " + articleDTO.getTitle() + " ist bereits in der Datenbank angelegt.");
            }
        });

        Article article = Article.builder()
                .title(articleDTO.getTitle())
                .category(articleDTO.getCategory())
                .price(articleDTO.getPrice())
                .build();


        articleRepository.save(article);
    }

    public Set<ArticleDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();
        Set<ArticleDTO> articleDTOS = new HashSet<>();

        for (Article article : articles) {
            articleDTOS.add(new ArticleDTO(
                    article.getTitle(),
                    article.getCategory(),
                    article.getPrice()
            ));
        }

        return articleDTOS;
    }

    public ArticleDTO getArticleById(int articleId) {
        if (articleRepository.findById(articleId).isEmpty()) {
            throw new NoSuchElementException("Keinen Artikel mit der Id " + articleId + " gefunden!");
        }

        Article article = articleRepository.findById(articleId).get();

        return new ArticleDTO(
                article.getTitle(),
                article.getCategory(),
                article.getPrice()
        );
    }

    public ArticleDTO updateArticlePrice(int articleId, ArticlePriceDTO articlePriceDTO) {
        if (articleRepository.findById(articleId).isEmpty()) {
            throw new NoSuchElementException("Kein Artikel mit der Id " + articleId + " gefunden!");
        }

        Article article = articleRepository.findById(articleId).get();
        article.setPrice(articlePriceDTO.getPrice());
        articleRepository.save(article);

        ArticleDTO articleDTO = new ArticleDTO(
                article.getTitle(),
                article.getCategory(),
                article.getPrice()
        );

        return articleDTO;
    }
}
