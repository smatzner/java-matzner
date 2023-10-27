package com.example.cinema.dtos;

import com.example.cinema.entities.MovieVersion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

