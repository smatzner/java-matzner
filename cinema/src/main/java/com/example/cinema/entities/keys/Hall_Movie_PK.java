package com.example.cinema.entities.keys;

import com.example.cinema.entities.Hall;
import com.example.cinema.entities.Movie;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hall_Movie_PK implements Serializable {
    private Hall hall;
    private Movie movie;
}
