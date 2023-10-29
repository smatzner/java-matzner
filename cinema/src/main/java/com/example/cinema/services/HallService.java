package com.example.cinema.services;

import com.example.cinema.dto.HallDTO;
import com.example.cinema.dto.ResponseHallDTO;
import com.example.cinema.dto.UpdateHallDTO;
import com.example.cinema.entities.Cinema;
import com.example.cinema.entities.Hall;
import com.example.cinema.entities.Hall_Movie;
import com.example.cinema.entities.MovieVersion;
import com.example.cinema.exceptions.CinemaNotFoundException;
import com.example.cinema.exceptions.IllegalMovieVersionAlterationException;
import com.example.cinema.exceptions.IllegalMovieVersionException;
import com.example.cinema.exceptions.MaxHallCapacityReachedException;
import com.example.cinema.repositories.CinemaRepository;
import com.example.cinema.repositories.HallRepository;
import com.example.cinema.repositories.Hall_MovieRepository;
import com.example.cinema.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class HallService {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final Hall_MovieRepository hall_movieRepository;

    public ResponseHallDTO createHall(HallDTO hallDTO) {
        Cinema cinema = getCinema(hallDTO);

        checkHallCapacity(cinema);

        MovieVersion movieVersion = getMovieVersion(hallDTO);

        Hall hall = Hall.builder()
                .cinema(cinema)
                .capacity(hallDTO.getCapacity())
                .occupiedSeats(hallDTO.getOccupiedSeats())
                .supportedMovieVersion(movieVersion)
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



    public ResponseHallDTO getHallById(int hallId) {
        Hall hall = getHall(hallId);

        return new ResponseHallDTO(
                hall.getId(),
                hall.getCinema().getId(),
                hall.getCapacity(),
                hall.getOccupiedSeats(),
                hall.getSupportedMovieVersion()
        );
    }


    public ResponseHallDTO updateHall(int hallId, UpdateHallDTO updateHallDTO) {
        Hall hall = getHall(hallId);

//        if(movieRepository.findByHallId(hallId) != null){
//            throw new IllegalArgumentException("Der Saal mit der ID " + hallId + " hat noch Filme zugeordnet. Sääle kann nur bearbeiten werden, wenn keine Filme zugeordnet sind");
//        }

        List<Hall_Movie> hall_movieList = hall_movieRepository.findAll();

        hall_movieList.forEach(hallMovie -> {
            if(hallMovie.getHall().getId() == hallId && hallMovie.getMovie() != null){
                throw new IllegalArgumentException("Der Saal mit der ID " + hallId + " hat noch Filme zugeordnet. Sääle kann nur bearbeiten werden, wenn keine Filme zugeordnet sind");
            }
        });

        MovieVersion movieVersion = getMovieVersion(updateHallDTO);

        if((hall.getSupportedMovieVersion() != movieVersion) && !(hall.getSupportedMovieVersion() == MovieVersion.DBOX && movieVersion == MovieVersion.R3D)){
            throw new IllegalMovieVersionAlterationException("Filmversion kann nur von DBOX auf R3D geändert werden");
        }

        hall.setCapacity(updateHallDTO.getCapacity());
        hall.setOccupiedSeats(updateHallDTO.getOccupiedSeats());
        hall.setSupportedMovieVersion(movieVersion);

        hallRepository.save(hall);

        return new ResponseHallDTO(
                hall.getId(),
                hall.getCinema().getId(),
                hall.getCapacity(),
                hall.getOccupiedSeats(),
                hall.getSupportedMovieVersion()
        );
    }



    private Cinema getCinema(HallDTO hallDTO) {
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(hallDTO.getCinemaId());

        if (cinemaOptional.isEmpty()) {
            throw new CinemaNotFoundException("Kein Kino mit der Id " + hallDTO.getCinemaId() + " gefunden!");
        }

        return cinemaOptional.get();
    }

    private Hall getHall(int hallId) {
        if (hallRepository.findById(hallId).isEmpty()) {
            throw new NoSuchElementException("Kein Saal mit der ID " + hallId + " gefunden!");
        }

        return hallRepository.findById(hallId).get();
    }

    private void checkHallCapacity(Cinema cinema) {
        List<Hall> hallList = hallRepository.findAll();
        int hallCount = (int) hallList.stream()
                .filter(hall -> hall.getCinema().getId() == cinema.getId())
                .count();
        if (hallCount >= cinema.getMaxHalls()) {
            throw new MaxHallCapacityReachedException("Die maximale Anzahl an Kinosälen wurden erreicht. Es kann keine neuer Saal hinzugefügt werden");
        }
    }


    private static MovieVersion getMovieVersion(HallDTO hallDTO) {
        MovieVersion movieVersion;
        try {
            movieVersion = MovieVersion.valueOf(hallDTO.getSupportedMovieVersion());
        } catch (IllegalArgumentException e) {
            throw new IllegalMovieVersionException("Die Filmversion " + hallDTO.getSupportedMovieVersion() + " kann nicht angelegt werden.");
        }
        return movieVersion;
    }

    private static MovieVersion getMovieVersion(UpdateHallDTO updateHallDTO) {
        MovieVersion movieVersion;
        try {
            movieVersion = MovieVersion.valueOf(updateHallDTO.getSupportedMovieVersion());
        } catch (IllegalArgumentException e) {
            throw new IllegalMovieVersionException("Die Filmversion " + updateHallDTO.getSupportedMovieVersion() + " kann nicht angelegt werden.");
        }
        return movieVersion;
    }
}
