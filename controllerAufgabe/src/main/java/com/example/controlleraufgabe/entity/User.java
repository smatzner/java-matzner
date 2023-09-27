package com.example.controlleraufgabe.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int userId;
    private String username;
    private String password;
    private int age;
}
