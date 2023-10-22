package com.example.entitiesaufgabe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponsePurchaseDTO {
    private int userId;
    private int purchaseId;
    private int articleId;
    private int qty;
    private double totalValue;
}
