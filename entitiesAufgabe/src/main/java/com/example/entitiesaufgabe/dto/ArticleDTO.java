package com.example.entitiesaufgabe.dto;

import com.example.entitiesaufgabe.entities.Category;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleDTO {
    private String title;
    private Category category;
    private double price;
}
