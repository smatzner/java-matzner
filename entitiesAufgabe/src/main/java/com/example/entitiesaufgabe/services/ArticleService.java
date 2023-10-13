package com.example.entitiesaufgabe.services;

import com.example.entitiesaufgabe.dto.ArticleDTO;
import com.example.entitiesaufgabe.dto.ArticlePriceDTO;
import com.example.entitiesaufgabe.entities.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

import static com.example.entitiesaufgabe.EntitiesAufgabeApplication.articles;

@RequiredArgsConstructor
@Service
public class ArticleService {
    public void saveArticle(ArticleDTO articleDTO) {
        Article article = new Article(
                articleDTO.getArticleId(),
                articleDTO.getArticleName(),
                articleDTO.getArticlePrice()
        );

        articles.add(article);
    }

    public Set<ArticleDTO> getArticles() {
        Set<ArticleDTO> articleDTOS = new HashSet<>();

        for (Article article : articles) {
            articleDTOS.add(new ArticleDTO(
                    article.getArticleId(),
                    article.getArticleName(),
                    article.getArticlePrice()
            ));
        }

        return articleDTOS;
    }

    public ArticleDTO getArticleById(int articleId) {
        ArticleDTO articleDTO = new ArticleDTO();

        for (Article article : articles){
            if(article.getArticleId() == articleId){
                articleDTO.setArticleId(article.getArticleId());
                articleDTO.setArticleName(article.getArticleName());
                articleDTO.setArticlePrice(article.getArticlePrice());

                return articleDTO;
            }
        }

        return null;
    }

    public ArticleDTO updateArticlePrice(int articleId, ArticlePriceDTO articlePriceDTO){
        ArticleDTO articleDTO = new ArticleDTO();

        for(Article article : articles){
            if(article.getArticleId() == articleId){
                article.setArticlePrice(articlePriceDTO.getArticlePrice());

                articleDTO.setArticleId(article.getArticleId());
                articleDTO.setArticleName(article.getArticleName());
                articleDTO.setArticlePrice(article.getArticlePrice());

                return articleDTO;
            }
        }

        return null;
    }
}
