package com.example.cinema.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseCinemaDTO {
    private int id;
    private String name;
    private String address;
    private String manager;
    private int maxHalls;
}

