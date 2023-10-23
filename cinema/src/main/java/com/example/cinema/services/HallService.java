package com.example.cinema.services;

import com.example.cinema.dtos.HallDTO;
import com.example.cinema.dtos.ResponseHallDTO;
import com.example.cinema.entities.Hall;
import com.example.cinema.repositories.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HallService {

    private final HallRepository hallRepository;

    public ResponseHallDTO createHall(HallDTO hallDTO) {
        Hall hall = Hall.builder()
                .capacity(hallDTO.getCapacity())
                .occupiedSeats(hallDTO.getOccupiedSeats())
                .supportedMovieVersion(hallDTO.getSupportedMovieVersion())
                .build();

        hallRepository.save(hall);

        return new ResponseHallDTO(
                hall.getId(),
                hall.getCapacity(),
                hall.getOccupiedSeats(),
                hall.getSupportedMovieVersion()
        );
    }
}
