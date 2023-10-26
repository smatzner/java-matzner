package com.example.cinema.services;

import com.example.cinema.dtos.HallDTO;
import com.example.cinema.dtos.ResponseHallDTO;
import com.example.cinema.dtos.UpdateHallDTO;
import com.example.cinema.entities.Cinema;
import com.example.cinema.entities.Hall;
import com.example.cinema.exceptions.CinemaNotFoundException;
import com.example.cinema.exceptions.MaxHallCapacityReachedException;
import com.example.cinema.repositories.CinemaRepository;
import com.example.cinema.repositories.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HallService {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;

    public ResponseHallDTO createHall(HallDTO hallDTO) {
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(hallDTO.getCinemaId());

        if (cinemaOptional.isEmpty()) {
            throw new CinemaNotFoundException("Kein Kino mit der Id " + hallDTO.getCinemaId() + " gefunden!");
        }
        Cinema cinema = cinemaOptional.get();

        checkHallCapacity(cinema);

        Hall hall = Hall.builder()
                .cinema(cinema)
                .capacity(hallDTO.getCapacity())
                .occupiedSeats(hallDTO.getOccupiedSeats())
                .supportedMovieVersion(hallDTO.getSupportedMovieVersion())
                .build();

        hallRepository.save(hall);

        return new ResponseHallDTO(
                hall.getId(),
                cinema.getId(),
                hall.getCapacity(),
                hall.getOccupiedSeats(),
                hall.getSupportedMovieVersion()
        );
    }

    public ResponseHallDTO getHallById(int hallId){
        if(hallRepository.findById(hallId).isEmpty()){
            throw new NoSuchElementException("Kein Saal mit der ID " + hallId + " gefunden!");
        }

        Hall hall = hallRepository.findById(hallId).get();

        return new ResponseHallDTO(
                hall.getId(),
                hall.getCinema().getId(),
                hall.getCapacity(),
                hall.getOccupiedSeats(),
                hall.getSupportedMovieVersion()
        );
    }

    public ResponseHallDTO updateHall(int hallId, UpdateHallDTO updateHallDTO){
        if(hallRepository.findById(hallId).isEmpty()){
            throw new NoSuchElementException("Kein Saal mit der ID " + hallId + " gefunden!");
        }

        Hall hall = hallRepository.findById(hallId).get();

        hall.setCapacity(updateHallDTO.getCapacity());
        hall.setOccupiedSeats(updateHallDTO.getOccupiedSeats());
        hall.setSupportedMovieVersion(updateHallDTO.getSupportedMovieVersion());

        //TODO: ändern nicht möglich wenn Filme zugeordnet sind
        hallRepository.save(hall);

        return new ResponseHallDTO(
                hall.getId(),
                hall.getCinema().getId(),
                hall.getCapacity(),
                hall.getOccupiedSeats(),
                hall.getSupportedMovieVersion() //TODO: darf nur von 5D auf 3D geändert werden
        );
    }

    private void checkHallCapacity(Cinema cinema) {
        List<Hall> hallList = hallRepository.findAll();
        int hallCount = (int) hallList.stream()
                .filter(hall -> hall.getCinema().getId() == cinema.getId())
                .count();
        if(hallCount >= cinema.getMaxHalls()){
            throw new MaxHallCapacityReachedException("Die maximale Anzahl an Kinosälen wurden erreicht. Es kann keine neuer Sall hinzugefügt werden");
        }
    }
}
