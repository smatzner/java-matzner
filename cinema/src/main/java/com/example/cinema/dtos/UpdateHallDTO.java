package com.example.cinema.dtos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateHallDTO {
    private int capacity;
    private int occupiedSeats;
    private String supportedMovieVersion;
}
