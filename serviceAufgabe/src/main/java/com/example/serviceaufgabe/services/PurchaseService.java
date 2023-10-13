package com.example.serviceaufgabe.services;

import com.example.serviceaufgabe.dto.UserPurchasesDTO;
import com.example.serviceaufgabe.dto.PurchaseDTO;
import com.example.serviceaufgabe.entities.Article;
import com.example.serviceaufgabe.entities.Purchase;
import com.example.serviceaufgabe.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.serviceaufgabe.ServiceAufgabeApplication.*;

@RequiredArgsConstructor
@Service
public class PurchaseService {
    public List<UserPurchasesDTO> addPurchase(int userId, PurchaseDTO purchaseDTO) {
        List<UserPurchasesDTO> userPurchases = new ArrayList<>();

        User purchasingUser = createNewPurchasingUser(userId);

        Purchase newPurchase = createNewPurchase(purchaseDTO, purchasingUser);

        purchases.put(purchasingUser, newPurchase);

        purchases.forEach((user, purchase) -> {
            if (user.getUserId() == purchasingUser.getUserId()) {
                userPurchases.add(new UserPurchasesDTO(
                        purchase.getUserId(),
                        purchase.getArticleId(),
                        purchase.getQty(),
                        purchase.getTotalValue()
                ));
            }
        });

        return userPurchases;
    }

    private Purchase createNewPurchase(PurchaseDTO purchaseDTO, User purchasingUser) {
        Optional<Article> purchasedArticle = articles.stream().filter(article -> article.getArticleId() == purchaseDTO.getArticleId()).findFirst();

        double totalValue = purchasedArticle.get().getArticlePrice() * purchaseDTO.getQty();

        Purchase newPurchase = new Purchase(
                purchasingUser.getUserId(),
                purchaseDTO.getArticleId(),
                purchaseDTO.getQty(),
                totalValue
        );
        return newPurchase;
    }

    private User createNewPurchasingUser(int userId){
        Optional<User> purchasingUser = users.stream().filter(user -> user.getUserId() == userId).findFirst();

        if(purchasingUser.isEmpty()) return null;

        User purchasingUserClone = new User(
                purchasingUser.get().getUserId(),
                purchasingUser.get().getUsername(),
                purchasingUser.get().getPassword(),
                purchasingUser.get().getAge());

        return purchasingUserClone;
    }
}
