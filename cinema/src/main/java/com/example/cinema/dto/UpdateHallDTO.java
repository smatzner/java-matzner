package com.example.cinema.dto;

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
