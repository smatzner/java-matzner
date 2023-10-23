package com.example.cinema.dtos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CinemaDTO {
    private String name;
    private String address;
    private String manager;
    private int maxHalls;
}
