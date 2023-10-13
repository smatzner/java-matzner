package com.example.serviceaufgabe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPurchasesDTO {
    private int userId;
    private int articleId;
    private int qty;
    private double totalValue;
}
