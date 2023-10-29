package com.example.cinema.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseCinemaWithHallsDTO {
    private int id;
    private String name;
    private String address;
    private String manager;
    private int maxHalls;
    private List<ResponseHallDTO> cinemaHalls;
}

