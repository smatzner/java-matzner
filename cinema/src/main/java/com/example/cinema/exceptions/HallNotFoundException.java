package com.example.cinema.exceptions;

public class HallNotFoundException extends RuntimeException {
    public HallNotFoundException(String msg) {
        super(msg);
    }
}
