package com.example.entitiesaufgabe.entities;

import com.example.entitiesaufgabe.entities.keys.Article_Purchase_PK;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "article_purchase")
@IdClass(Article_Purchase_PK.class)
public class Article_Purchase {
    @Id
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Id
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private int qty;

    @Transient
    public double totalValue(){
        return qty * article.getPrice();
    }
}
