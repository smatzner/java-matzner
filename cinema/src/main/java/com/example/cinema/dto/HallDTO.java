package com.example.cinema.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HallDTO {
    private int cinemaId;
    private int capacity;
    private int occupiedSeats;
    private String supportedMovieVersion;
}

