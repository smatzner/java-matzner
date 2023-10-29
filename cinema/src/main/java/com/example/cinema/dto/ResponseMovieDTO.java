package com.example.cinema.dto;

import com.example.cinema.entities.Hall_Movie;
import com.example.cinema.entities.MovieVersion;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseMovieDTO {
    private int id;
//    private int hallId;
//    List<Hall_Movie> hallMovieList = null;
    private String title;
    private String mainCharacter;
    private String description;
    private Date premieredAt;
    private MovieVersion movieVersion;
}
