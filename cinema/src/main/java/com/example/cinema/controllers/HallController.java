package com.example.cinema.controllers;

import com.example.cinema.dto.HallDTO;
import com.example.cinema.dto.MovieDTO;
import com.example.cinema.dto.ResponseHallDTO;
import com.example.cinema.dto.UpdateHallDTO;
import com.example.cinema.exceptions.*;
import com.example.cinema.services.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/hall")
public class HallController {

    private final HallService hallService;

    @PostMapping()
    public ResponseEntity<?> createHall(@RequestBody HallDTO hallDTO) {
        try {
            ResponseHallDTO responseHallDTO = hallService.createHall(hallDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseHallDTO);
        } catch (CinemaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MaxHallCapacityReachedException | IllegalMovieVersionException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @GetMapping("{hallId}")
    public ResponseEntity<?> getHallById(@PathVariable int hallId) {
        try {
            ResponseHallDTO responseHallDTO = hallService.getHallById(hallId);
            return ResponseEntity.status(HttpStatus.OK).body(responseHallDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{hallId}")
    public ResponseEntity<?> updateHall(@PathVariable int hallId, @RequestBody UpdateHallDTO updateHallDTO){
        try{
            ResponseHallDTO responseHallDTO = hallService.updateHall(hallId,updateHallDTO);
            return ResponseEntity.status(HttpStatus.OK).body(responseHallDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MovieVersionNotSupportedException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (IllegalMovieVersionAlterationException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("{hallId}/movie")
    public ResponseEntity<?> addMovieToHall(@PathVariable int hallId, @RequestBody MovieDTO movieDTO){
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("I'm a teapot");
    }
}
