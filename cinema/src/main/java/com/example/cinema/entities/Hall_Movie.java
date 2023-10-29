package com.example.cinema.entities;

import com.example.cinema.entities.keys.Hall_Movie_PK;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "hall_movie")
@IdClass(Hall_Movie_PK.class)
public class Hall_Movie {
    @Id
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
