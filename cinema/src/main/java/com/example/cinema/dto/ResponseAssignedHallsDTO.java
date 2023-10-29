package com.example.cinema.dto;

import com.example.cinema.entities.Hall;
import com.example.cinema.entities.Movie;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseAssignedHallsDTO {
    private ResponseMovieDTO movie;
    private List<ResponseHallDTO> halls;
}
