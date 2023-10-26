package com.example.cinema.controllers;

import com.example.cinema.dtos.HallDTO;
import com.example.cinema.dtos.ResponseHallDTO;
import com.example.cinema.exceptions.CinemaNotFoundException;
import com.example.cinema.exceptions.MaxHallCapacityReachedException;
import com.example.cinema.services.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        } catch (MaxHallCapacityReachedException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("I am a teapot");
        }
    }
}
