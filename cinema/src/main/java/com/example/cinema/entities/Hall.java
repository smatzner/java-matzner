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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieVersion supportedMovieVersion;

//    @OneToMany(mappedBy = "hall")
//    private List<Movie> movies;

    @OneToMany(mappedBy = "hall")
    private List<Hall_Movie> hallMovieList;
}
