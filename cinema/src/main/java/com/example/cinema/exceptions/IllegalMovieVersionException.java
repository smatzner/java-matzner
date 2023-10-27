package com.example.cinema.exceptions;

public class IllegalMovieVersionException extends RuntimeException {
    public IllegalMovieVersionException(String msg) {
        super(msg);
    }
}
