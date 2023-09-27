package com.example.controlleraufgabe.controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping
    public String printMyFirstRequest(){
        return "Mein erster Request";
    }
}
