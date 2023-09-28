package com.example.controlleraufgabe.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetUserDTO {
    private int userId;
    private String username;
    private int age;
}
