package com.example.cinema.services;

import com.example.cinema.dto.*;
import com.example.cinema.entities.Hall;
import com.example.cinema.entities.Hall_Movie;
import com.example.cinema.entities.Movie;
import com.example.cinema.entities.MovieVersion;
import com.example.cinema.exceptions.HallNotFoundException;
import com.example.cinema.exceptions.IllegalMovieVersionException;
import com.example.cinema.exceptions.MovieVersionNotSupportedException;
import com.example.cinema.repositories.HallRepository;
import com.example.cinema.repositories.Hall_MovieRepository;
import com.example.cinema.repositories.MovieRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final Hall_MovieRepository hall_movieRepository;

    public ResponseMovieDTO createMovie(MovieDTO movieDTO) {

        checkIfMovieAlreadyExists(movieDTO);
////        TODO: zu addMovieToHall
//        Hall hall = getHall(movieDTO);
//
        MovieVersion movieVersion = getMovieVersion(movieDTO);

////        TODO: zu addMovieToHall
//        checkIfMovieVersionIsSupported(hall, movieVersion);

        Movie movie = Movie.builder()
                .title(movieDTO.getTitle())
                .mainCharacter(movieDTO.getMainCharacter())
                .description(movieDTO.getDescription())
                .premieredAt(movieDTO.getPremieredAt())
                .movieVersion(movieVersion)
                .build();

        movieRepository.save(movie);

        return ResponseMovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .mainCharacter(movie.getMainCharacter())
                .description(movie.getDescription())
                .premieredAt(movie.getPremieredAt())
                .movieVersion(movieVersion)
                .build();
    }

    public ResponseMovieDTO updateMovie(int movieId, MovieDTO movieDTO) {

        Movie movie = getMovie(movieId, movieDTO);

        MovieVersion movieVersion = getMovieVersion(movieDTO);

//        Hall hall = getHall(movieDTO);

//        checkIfMovieVersionIsSupported(hall, movieVersion);

        movie.setTitle(movieDTO.getTitle());
        movie.setMainCharacter(movieDTO.getMainCharacter());
        movie.setDescription(movieDTO.getDescription());
        movie.setPremieredAt(movieDTO.getPremieredAt());
        movie.setMovieVersion(movieVersion);

        movieRepository.save(movie);

        return new ResponseMovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getMainCharacter(),
                movie.getDescription(),
                movie.getPremieredAt(),
                movie.getMovieVersion()
        );
    }

    public ResponseAssignedHallsDTO addMovieToHall(int movieId, AssignHallDTO assignHallDTO) {
//        TODO: check if movie already assigned to hall
        Hall hall = getHall(assignHallDTO);
        Movie movie = getMovie(movieId);

        checkIfMovieVersionIsSupported(hall, movie.getMovieVersion());

        Hall_Movie hallMovie = new Hall_Movie(hall, movie);

        hall_movieRepository.save(hallMovie);

        ResponseMovieDTO responseMovieDTO = ResponseMovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .mainCharacter(movie.getMainCharacter())
                .description(movie.getDescription())
                .premieredAt(movie.getPremieredAt())
                .movieVersion(movie.getMovieVersion())
                .build();

        List<Hall_Movie> hallMovieList = hall_movieRepository.findAll();
        List<ResponseHallDTO> assignedHalls = new ArrayList<>();

        hallMovieList.forEach(assignedMovie -> {
            if (assignedMovie.getMovie().getId() == movieId) {
                Hall assignedHall = assignedMovie.getHall();
                ResponseHallDTO responseHallDTO = ResponseHallDTO.builder()
                        .id(assignedHall.getId())
                        .cinemaId(assignedHall.getCinema().getId())
                        .capacity(assignedHall.getCapacity())
                        .occupiedSeats(assignedHall.getOccupiedSeats())
                        .supportedMovieVersion(assignedHall.getSupportedMovieVersion())
                        .build();
                assignedHalls.add(responseHallDTO);
            }
        });

        return new ResponseAssignedHallsDTO(responseMovieDTO, assignedHalls);
    }


//    private Hall getHall(MovieDTO movieDTO) {
//        Optional<Hall> hallOptional = hallRepository.findById(movieDTO.getHallId());
//
//        if (hallOptional.isEmpty()) {
//            throw new HallNotFoundException("Kein Saal mit der ID " + movieDTO.getHallId() + " gefunden!");
//        }
//
//        return hallOptional.get();
//    }

    private Hall getHall(AssignHallDTO assignHallDTO) {
        Optional<Hall> hallOptional = hallRepository.findById(assignHallDTO.getHallId());

        if (hallOptional.isEmpty()) {
            throw new HallNotFoundException("Kein Saal mit der ID " + assignHallDTO.getHallId() + " gefunden!");
        }

        return hallOptional.get();
    }


    private static void checkIfMovieVersionIsSupported(Hall hall, MovieVersion movieVersion) {
        if (hall.getSupportedMovieVersion() != movieVersion) {
            throw new MovieVersionNotSupportedException("Der Saal mit der ID " + hall.getId() + " unterstützt nicht die Filmversion " + movieVersion);
        }
    }

    private void checkIfMovieAlreadyExists(MovieDTO movieDTO) {
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> {
            if (movie.getTitle().equalsIgnoreCase(movieDTO.getTitle())) {
                throw new EntityExistsException("Der Film mit dem Namen " + movieDTO.getTitle() + " ist bereits vorhanden.");
            }
        });
    }

    private Movie getMovie(int movieId, MovieDTO movieDTO) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        List<Movie> movies = movieRepository.findAll();

        if (movieOptional.isEmpty()) {
            throw new NoSuchElementException("Kein Film mit der ID " + movieId + " gefunden!");
        }

        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieDTO.getTitle())) {
                if(movie.getId() == movieId) break;
                throw new EntityExistsException("Der Film mit dem Namen " + movieDTO.getTitle() + " ist bereits vorhanden.");
            }
        }

        return movieOptional.get();
    }


    private Movie getMovie(int movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new NoSuchElementException("Kein Film mit der ID " + movieId + " gefunden!");
        }

        return movieOptional.get();
    }


    private static MovieVersion getMovieVersion(MovieDTO movieDTO) {
        MovieVersion movieVersion;
        try {
            movieVersion = MovieVersion.valueOf(movieDTO.getMovieVersion());
        } catch (IllegalArgumentException e) {
            throw new IllegalMovieVersionException("Die Filmversion " + movieDTO.getMovieVersion() + " ist keine gültige Filmversion");
        }
        return movieVersion;
    }

}
