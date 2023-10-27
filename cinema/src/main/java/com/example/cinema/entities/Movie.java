package com.example.cinema.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @Column(nullable = false)
    private String title;

    private String mainCharacter;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date premieredAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieVersion movieVersion;
}
