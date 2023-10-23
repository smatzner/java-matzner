package com.example.cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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

    private int capacity;

    private int occupiedSeats;

    private String supportedMovieVersion;
}
