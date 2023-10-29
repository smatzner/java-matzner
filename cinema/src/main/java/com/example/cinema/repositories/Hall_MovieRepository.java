package com.example.cinema.repositories;

import com.example.cinema.entities.Hall;
import com.example.cinema.entities.Hall_Movie;
import com.example.cinema.entities.Movie;
import com.example.cinema.entities.keys.Hall_Movie_PK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Hall_MovieRepository extends JpaRepository<Hall_Movie, Hall_Movie_PK>{
    List<Hall_Movie> findByMovie(Movie movie);
}
