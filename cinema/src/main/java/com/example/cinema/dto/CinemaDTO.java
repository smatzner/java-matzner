package com.example.cinema.dto;

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
