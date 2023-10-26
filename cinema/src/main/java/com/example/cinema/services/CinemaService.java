package com.example.cinema.services;

import com.example.cinema.dtos.CinemaDTO;
import com.example.cinema.dtos.ResponseCinemaDTO;
import com.example.cinema.dtos.ResponseCinemaWithHallsDTO;
import com.example.cinema.dtos.ResponseHallDTO;
import com.example.cinema.entities.Cinema;
import com.example.cinema.entities.Hall;
import com.example.cinema.repositories.CinemaRepository;
import com.example.cinema.repositories.HallRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

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

    // TODO: löschen
    public Set<ResponseCinemaDTO> getCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        Set<ResponseCinemaDTO> responseCinemaWithHallsDTOS = new HashSet<>();

        cinemas.forEach(cinema -> {
            responseCinemaWithHallsDTOS.add(new ResponseCinemaDTO(
                    cinema.getId(),
                    cinema.getName(),
                    cinema.getAddress(),
                    cinema.getManager(),
                    cinema.getMaxHalls()
            ));
        });

        return responseCinemaWithHallsDTOS;
    }

    public ResponseCinemaWithHallsDTO getCinemaById(int cinemaId){
        if(cinemaRepository.findById(cinemaId).isEmpty()){
            throw new NoSuchElementException("Kein Kino mit der ID " + cinemaId + " gefunden!");
        }

        Cinema cinema = cinemaRepository.findById(cinemaId).get();

        List<ResponseHallDTO> cinemaHalls = getCinemaHalls(cinemaId);

        return new ResponseCinemaWithHallsDTO(
                cinema.getId(),
                cinema.getName(),
                cinema.getAddress(),
                cinema.getManager(),
                cinema.getMaxHalls(),
                cinemaHalls
        );
    }


    public void deleteCinema(int cinemaId) {
        //TODO: Kino nur löschen, wenn keine Filme zugeordnet sind
        if(cinemaRepository.findById(cinemaId).isEmpty()){
            throw new NoSuchElementException("Kein Kino mit der ID " + cinemaId + " gefunden!");
        }

        deleteCinemaHalls(cinemaId);

        Cinema cinema = cinemaRepository.findById(cinemaId).get();

        cinemaRepository.delete(cinema);
    }

    private void deleteCinemaHalls(int cinemaId) {
        List<Hall> hallList = hallRepository.findAll();
        hallList.forEach(hall -> {
            if(hall.getCinema().getId() == cinemaId){
                hallRepository.delete(hall);
            }
        });
    }


    private List<ResponseHallDTO> getCinemaHalls(int cinemaId) {
        List<Hall> hallList = hallRepository.findAll();
        List<ResponseHallDTO> responseHallDTOS = new ArrayList<>();
        hallList.forEach(hall -> {
            if(hall.getCinema().getId() == cinemaId){
                ResponseHallDTO responseHallDTO = ResponseHallDTO.builder()
                        .id(hall.getId())
                        .capacity(hall.getCapacity())
                        .occupiedSeats(hall.getOccupiedSeats())
                        .supportedMovieVersion(hall.getSupportedMovieVersion())
                        .build();
                responseHallDTOS.add(responseHallDTO);
            }
        });
        return responseHallDTOS;
    }
}
