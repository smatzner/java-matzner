package com.example.controlleraufgabe.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping
    public String printMyFirstRequest() {
        return "Mein erster Request";
    }

    @PostMapping
    public boolean isEven(@RequestBody int num) {
        return num % 2 == 0;
    }

    @GetMapping("{myString}")
    public String shortenString(@PathVariable String myString) {
        if (myString.length() <= 3) {
            return "String zu kurz!";
        }
        return myString.substring(3);
    }
}
