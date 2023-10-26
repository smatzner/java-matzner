package com.example.cinema.exceptions;

public class MovieVersionNotSupportedException extends RuntimeException {
    public MovieVersionNotSupportedException(String msg) {
        super(msg);
    }
}
