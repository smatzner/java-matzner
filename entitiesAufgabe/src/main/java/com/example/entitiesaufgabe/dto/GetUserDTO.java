package com.example.entitiesaufgabe.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetUserDTO {
    private int id;
    private String username;
    private int age;
}
