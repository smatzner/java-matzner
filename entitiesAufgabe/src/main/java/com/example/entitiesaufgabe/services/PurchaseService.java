package com.example.entitiesaufgabe.services;

import com.example.entitiesaufgabe.dto.ResponsePurchaseDTO;
import com.example.entitiesaufgabe.dto.PurchaseDTO;
import com.example.entitiesaufgabe.entities.Article;
import com.example.entitiesaufgabe.entities.Article_Purchase;
import com.example.entitiesaufgabe.entities.Purchase;
import com.example.entitiesaufgabe.entities.User;
import com.example.entitiesaufgabe.exceptions.ArticleNotFoundException;
import com.example.entitiesaufgabe.exceptions.UserNotFoundException;
import com.example.entitiesaufgabe.repositories.ArticleRepository;
import com.example.entitiesaufgabe.repositories.Article_PurchaseRepository;
import com.example.entitiesaufgabe.repositories.PurchaseRepository;
import com.example.entitiesaufgabe.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    Article_PurchaseRepository articlePurchaseRepository;

    public ResponsePurchaseDTO addPurchase(PurchaseDTO purchaseDTO) {
        Optional<User> userOptional = userRepository.findById(purchaseDTO.getUserId());
        Optional<Article> articleOptional = articleRepository.findById(purchaseDTO.getArticleId());

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User mit der Id " + purchaseDTO.getUserId() + " nicht gefunden!");
        }
        User user = userOptional.get();

        if (articleOptional.isEmpty()) {
            throw new ArticleNotFoundException("Artikel mit der Id" + purchaseDTO.getArticleId() + " nicht gefunden!");
        }
        Article article = articleOptional.get();

        Purchase purchase = Purchase.builder()
                .user(user)
                .build();

        purchaseRepository.save(purchase);

        Article_Purchase articlePurchase = Article_Purchase.builder()
                .article(article)
                .purchase(purchase)
                .qty(purchaseDTO.getQty())
                .build();

        articlePurchaseRepository.save(articlePurchase);

        return new ResponsePurchaseDTO(
                user.getUserId(),
                purchase.getPurchaseId(),
                article.getArticleId(),
                articlePurchase.getQty(),
                articlePurchase.totalValue()
        );
    }

//    private User createCustomer(int userId) {
//        Optional<User> userOptional = users.stream().filter(user -> user.getUserId() == userId).findFirst();
//
//        // TODO: impl Exception
//        if (userOptional.isEmpty()) return null;
//
//        User user = userOptional.get();
//
//        User customer = User.builder()
//                .userId(user.getUserId())
//                .username(user.getUsername())
//                .build();
//
//
//        return customer;
//    }

//    private Purchase createNewPurchase(PurchaseDTO purchaseDTO, User purchasingUser) {
//        Optional<Article> purchasedArticle = articles.stream().filter(article -> article.getArticleId() == purchaseDTO.getArticleId()).findFirst();
//
//        double totalValue = purchasedArticle.get().getArticlePrice() * purchaseDTO.getQty();
//
//        Purchase newPurchase = new Purchase(
//                purchaseDTO.getArticleId(),
//                purchaseDTO.getQty(),
//                totalValue
//        );
//        return newPurchase;
//    }

}
