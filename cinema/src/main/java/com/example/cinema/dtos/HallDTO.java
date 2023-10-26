package com.example.cinema.dtos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HallDTO {
    private int cinemaId;
    private int capacity;
    private int occupiedSeats;
    private String supportedMovieVersion;
}
