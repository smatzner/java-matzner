package com.example.cinema.controllers;

import com.example.cinema.dtos.CinemaDTO;
import com.example.cinema.dtos.ResponseCinemaDTO;
import com.example.cinema.services.CinemaService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
