package com.example.cinema.dtos;

import lombok.*;

import java.util.List;

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

