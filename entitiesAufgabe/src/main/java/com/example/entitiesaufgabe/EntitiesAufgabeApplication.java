package com.example.entitiesaufgabe;

import com.example.entitiesaufgabe.entities.Article;
import com.example.entitiesaufgabe.entities.Purchase;
import com.example.entitiesaufgabe.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class EntitiesAufgabeApplication {
    public static Set<User> users = new HashSet<User>();
    public static Set<Article> articles = new HashSet<Article>();
    public static Map<User, Purchase> purchases = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(EntitiesAufgabeApplication.class, args);
    }

}
