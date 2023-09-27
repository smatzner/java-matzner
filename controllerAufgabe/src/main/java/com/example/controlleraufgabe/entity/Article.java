package com.example.controlleraufgabe.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article {
    private int articleId;
    private String articleName;
    private double articlePrice;
}
