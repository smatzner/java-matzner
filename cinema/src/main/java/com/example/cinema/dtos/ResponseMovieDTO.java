package com.example.cinema.dtos;

import com.example.cinema.entities.MovieVersion;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseMovieDTO {
    private int id;
    private int hallId;
    private String title;
    private String mainCharacter;
    private String description;
    private Date premieredAt;
    private MovieVersion movieVersion;
}
