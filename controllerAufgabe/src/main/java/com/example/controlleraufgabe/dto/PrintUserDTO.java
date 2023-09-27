package com.example.controlleraufgabe.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PrintUserDTO {
    private int userId;
    private String username;
    private int age;
}
