package com.example.entitiesaufgabe.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Purchase {
    private int userId;
    private int articleId;
    private int qty;
    private double totalValue;
}
