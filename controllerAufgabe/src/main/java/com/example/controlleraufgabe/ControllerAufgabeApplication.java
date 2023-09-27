package com.example.controlleraufgabe;

import com.example.controlleraufgabe.entity.Article;
import com.example.controlleraufgabe.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ControllerAufgabeApplication {
    public Set<User> users = new HashSet<User>();
    public Set<Article> articles = new HashSet<Article>();

    public static void main(String[] args) {
                SpringApplication.run(ControllerAufgabeApplication.class, args);
    }

}
