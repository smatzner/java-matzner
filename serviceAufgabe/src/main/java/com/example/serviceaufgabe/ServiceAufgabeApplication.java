package com.example.serviceaufgabe;

import com.example.serviceaufgabe.entities.Article;
import com.example.serviceaufgabe.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ServiceAufgabeApplication {
    public static Set<User> users = new HashSet<User>();
    public static Set<Article> articles = new HashSet<Article>();

    public static void main(String[] args) {
        SpringApplication.run(ServiceAufgabeApplication.class, args);
    }

}
