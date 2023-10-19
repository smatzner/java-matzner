package com.example.entitiesaufgabe.services;

import com.example.entitiesaufgabe.dto.UserPurchasesDTO;
import com.example.entitiesaufgabe.dto.PurchaseDTO;
import com.example.entitiesaufgabe.entities.Article;
import com.example.entitiesaufgabe.entities.Purchase;
import com.example.entitiesaufgabe.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.entitiesaufgabe.EntitiesAufgabeApplication.*;

@RequiredArgsConstructor
@Service
public class PurchaseService {


    public List<UserPurchasesDTO> addPurchase(int userId, PurchaseDTO purchaseDTO) {
        List<UserPurchasesDTO> userPurchases = new ArrayList<>();

        User customer = createCustomer(userId);

        // TODO: impl Repo

        return userPurchases;
    }

    private User createCustomer(int userId){
        Optional<User> userOptional = users.stream().filter(user -> user.getUserId() == userId).findFirst();

        // TODO: impl Exception
        if(userOptional.isEmpty()) return null;

        User user = userOptional.get();

        User customer = User.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .build();


        return customer;
    }

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
