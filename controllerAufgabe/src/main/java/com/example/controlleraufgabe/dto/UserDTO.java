package com.example.controlleraufgabe.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int userId;
    private String username;
    private String password;
    private int age;
}
