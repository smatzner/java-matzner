package com.example.cinema.services;

import com.example.cinema.dtos.CinemaDTO;
import com.example.cinema.dtos.ResponseCinemaDTO;
import com.example.cinema.entities.Cinema;
import com.example.cinema.repositories.CinemaRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Entity;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public ResponseCinemaDTO createCinema(CinemaDTO cinemaDTO){

        List<Cinema> cinemas = cinemaRepository.findAll();
        cinemas.forEach(cinema -> {
            if(cinema.getName().equalsIgnoreCase(cinemaDTO.getName())){
                throw new EntityExistsException("Das Kino mit dem Namen " + cinemaDTO.getName() + " ist bereits vorhanden");
            }
            if(cinema.getAddress().equalsIgnoreCase(cinemaDTO.getAddress())){
                throw new EntityExistsException("Es existiert bereits ein Kino an der Adresse " + cinemaDTO.getAddress());
            }
        });

        Cinema cinema = Cinema.builder()
                .name(cinemaDTO.getName())
                .address(cinemaDTO.getAddress())
                .manager(cinemaDTO.getManager())
                .maxHalls(cinemaDTO.getMaxHalls())
                .build();

        cinemaRepository.save(cinema);

        return new ResponseCinemaDTO(
                cinema.getId(),
                cinema.getName(),
                cinema.getAddress(),
                cinema.getManager(),
                cinema.getMaxHalls()
        );
    }
}
