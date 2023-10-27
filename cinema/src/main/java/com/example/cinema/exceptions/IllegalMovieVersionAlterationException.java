package com.example.cinema.exceptions;

public class IllegalMovieVersionAlterationException extends RuntimeException {
    public IllegalMovieVersionAlterationException(String msg) {
        super(msg);
    }
}
