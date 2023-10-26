package com.example.cinema.controllers;

import com.example.cinema.dtos.MovieDTO;
import com.example.cinema.dtos.ResponseMovieDTO;
import com.example.cinema.dtos.UpdateHallDTO;
import com.example.cinema.dtos.UpdateMovieDTO;
import com.example.cinema.exceptions.MovieVersionNotSupportedException;
import com.example.cinema.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody MovieDTO movieDTO){
        try{
            ResponseMovieDTO responseMovieDTO = movieService.createMovie(movieDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMovieDTO);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MovieVersionNotSupportedException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @PutMapping("{movieId}")
    public ResponseEntity<?> updateMovie(@PathVariable int movieId, @RequestBody UpdateMovieDTO updateMovieDTO){
        try{
            ResponseMovieDTO responseMovieDTO = movieService.updateMovie(movieId,updateMovieDTO);
            return ResponseEntity.status(HttpStatus.OK).body(responseMovieDTO);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
