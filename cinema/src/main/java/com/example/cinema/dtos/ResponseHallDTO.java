package com.example.cinema.dtos;

import com.example.cinema.entities.MovieVersion;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseHallDTO {
    private int id;
    private int cinemaId;
    private int capacity;
    private int occupiedSeats;
    private MovieVersion supportedMovieVersion;
}
