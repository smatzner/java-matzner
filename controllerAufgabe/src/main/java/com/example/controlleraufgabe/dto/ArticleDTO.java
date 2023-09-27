package com.example.controlleraufgabe.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleDTO {
    private int articleId;
    private String articleName;
    private double articlePrice;
}
