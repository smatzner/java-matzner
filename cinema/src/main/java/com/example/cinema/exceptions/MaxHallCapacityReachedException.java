package com.example.cinema.exceptions;

public class MaxHallCapacityReachedException extends RuntimeException {
    public MaxHallCapacityReachedException(String msg){
        super(msg);
    }
}
