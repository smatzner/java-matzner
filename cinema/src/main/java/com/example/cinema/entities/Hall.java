package com.example.cinema.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    private int capacity;

    private int occupiedSeats;

    private String supportedMovieVersion;

    @OneToMany(mappedBy = "hall")
    private List<Movie> movies;
}
