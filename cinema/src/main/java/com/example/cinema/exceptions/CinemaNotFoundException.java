package com.example.cinema.exceptions;

public class CinemaNotFoundException extends RuntimeException {
    public CinemaNotFoundException(String msg) {
        super(msg);
    }
}
