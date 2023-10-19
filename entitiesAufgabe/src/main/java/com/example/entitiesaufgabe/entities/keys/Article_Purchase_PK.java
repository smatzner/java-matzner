package com.example.entitiesaufgabe.entities.keys;

import com.example.entitiesaufgabe.entities.Article;
import com.example.entitiesaufgabe.entities.Purchase;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article_Purchase_PK implements Serializable {
    private Article article;
    private Purchase purchase;
}
