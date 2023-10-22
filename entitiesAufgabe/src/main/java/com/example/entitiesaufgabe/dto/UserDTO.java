package com.example.entitiesaufgabe.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private int age;
}
