package com.example.cinema.controllers;

import com.example.cinema.dtos.HallDTO;
import com.example.cinema.dtos.ResponseHallDTO;
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

    @PostMapping("api/hall")
    public ResponseEntity<?> createHall(@RequestBody HallDTO hallDTO) {
        try {
            ResponseHallDTO responseHallDTO = hallService.createHall(hallDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseHallDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("I am a teapot");
        }
    }
}
