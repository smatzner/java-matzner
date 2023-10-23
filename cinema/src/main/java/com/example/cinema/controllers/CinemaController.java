package com.example.cinema.controllers;

import com.example.cinema.dtos.CinemaDTO;
import com.example.cinema.dtos.ResponseCinemaDTO;
import com.example.cinema.services.CinemaService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cinema")
public class CinemaController {
    private final CinemaService cinemaService;

    @PostMapping("/create")
    public ResponseEntity<?> createCinema(@RequestBody CinemaDTO cinemaDTO) {
        try {
            ResponseCinemaDTO responseCinemaDTO = cinemaService.createCinema(cinemaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseCinemaDTO);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCinemas() {
        Set<ResponseCinemaDTO> responseCinemaDTOS = cinemaService.getCinemas();
        return ResponseEntity.status(HttpStatus.OK).body(responseCinemaDTOS);
    }

    @GetMapping("{cinemaId}")
    public ResponseEntity<?> getCinemaById(@PathVariable int cinemaId){
        try{
            ResponseCinemaDTO responseCinemaDTO = cinemaService.getCinemaById(cinemaId);
            return ResponseEntity.status(HttpStatus.OK).body(responseCinemaDTO);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{cinemaId}")
    public ResponseEntity<?> deleteCinema(@PathVariable int cinemaId){
        try{
            cinemaService.deleteCinema(cinemaId);
            return ResponseEntity.status(HttpStatus.OK).body("Kino gelöscht");
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
