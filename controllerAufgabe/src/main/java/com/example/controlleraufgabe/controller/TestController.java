package com.example.controlleraufgabe.controller;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@RestController
@RequestMapping("api/test")
public class TestController {
    
}
