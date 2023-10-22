package com.example.entitiesaufgabe.exceptions;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(String msg) {
        super(msg);
    }
}
