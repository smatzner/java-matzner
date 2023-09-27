package com.example.controlleraufgabe.controller;

import lombok.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping
    public String printMyFirstRequest(){
        return "Mein erster Request";
    }

    @PostMapping
    public boolean isEven(@RequestBody int num){
        return num % 2 == 0;
    }
}
