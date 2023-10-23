package com.example.cinema.services;

import com.example.cinema.CinemaApplication;
import com.example.cinema.dtos.CinemaDTO;
import com.example.cinema.dtos.ResponseCinemaDTO;
import com.example.cinema.entities.Cinema;
import com.example.cinema.repositories.CinemaRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

    public Set<ResponseCinemaDTO> getCinemas() {
        // TODO: Saele anzeigeen
        List<Cinema> cinemas = cinemaRepository.findAll();
        Set<ResponseCinemaDTO> responseCinemaDTOS = new HashSet<>();

        cinemas.forEach(cinema -> {
            responseCinemaDTOS.add(new ResponseCinemaDTO(
                    cinema.getId(),
                    cinema.getName(),
                    cinema.getAddress(),
                    cinema.getManager(),
                    cinema.getMaxHalls()
            ));
        });

        return responseCinemaDTOS;
    }

    public ResponseCinemaDTO getCinemaById(int cinemaId){
        // TODO: Saele anzeigen
        if(cinemaRepository.findById(cinemaId).isEmpty()){
            throw new NoSuchElementException("Kein Kino mit der ID " + cinemaId + " gefunden!");
        }

        Cinema cinema = cinemaRepository.findById(cinemaId).get();

        return new ResponseCinemaDTO(
                cinema.getId(),
                cinema.getName(),
                cinema.getAddress(),
                cinema.getManager(),
                cinema.getMaxHalls()
        );
    }

    public void deleteCinema(int cinemaId) {
        //TODO: Kino nur löschen, wenn keine Filme zugeordnet sind
        if(cinemaRepository.findById(cinemaId).isEmpty()){
            throw new NoSuchElementException("Kein Kino mit der ID " + cinemaId + " gefunden!");
        }

        Cinema cinema = cinemaRepository.findById(cinemaId).get();

        cinemaRepository.delete(cinema);
    }
}
