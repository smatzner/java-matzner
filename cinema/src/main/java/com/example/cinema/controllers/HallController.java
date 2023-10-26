package com.example.cinema.controllers;

import com.example.cinema.dtos.HallDTO;
import com.example.cinema.dtos.ResponseHallDTO;
import com.example.cinema.dtos.UpdateHallDTO;
import com.example.cinema.exceptions.CinemaNotFoundException;
import com.example.cinema.exceptions.MaxHallCapacityReachedException;
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
        } catch (MaxHallCapacityReachedException e) {
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
        }
    }
}
