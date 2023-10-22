package com.example.entitiesaufgabe.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDTO {
    private int userId;
    private int articleId;
    private int qty;
}
