package com.example.cinema.services;

import com.example.cinema.dtos.MovieDTO;
import com.example.cinema.dtos.ResponseMovieDTO;
import com.example.cinema.entities.Hall;
import com.example.cinema.entities.Movie;
import com.example.cinema.entities.MovieVersion;
import com.example.cinema.exceptions.HallNotFoundException;
import com.example.cinema.exceptions.IllegalMovieVersionException;
import com.example.cinema.exceptions.MovieVersionNotSupportedException;
import com.example.cinema.repositories.HallRepository;
import com.example.cinema.repositories.MovieRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;

    public ResponseMovieDTO createMovie(MovieDTO movieDTO) {
        List<Movie> movies = movieRepository.findAll();
        Optional<Hall> hallOptional = hallRepository.findById(movieDTO.getHallId());

        checkIfMovieExists(movieDTO, movies);

        Hall hall = getHall(movieDTO, hallOptional);

        MovieVersion movieVersion = getMovieVersion(movieDTO);

        checkIfMovieVersionIsSupported(hall, movieVersion);

        Movie movie = Movie.builder()
                .hall(hall)
                .title(movieDTO.getTitle())
                .mainCharacter(movieDTO.getMainCharacter())
                .description(movieDTO.getDescription())
                .premieredAt(movieDTO.getPremieredAt())
                .movieVersion(movieVersion)
                .build();

        movieRepository.save(movie);

        return new ResponseMovieDTO(
                movie.getId(),
                movie.getHall().getId(),
                movie.getTitle(),
                movie.getMainCharacter(),
                movie.getDescription(),
                movie.getPremieredAt(),
                movie.getMovieVersion()
        );
    }

    public ResponseMovieDTO updateMovie(int movieId, MovieDTO movieDTO) {
        List<Movie> movies = movieRepository.findAll();
        Optional<Hall> hallOptional = hallRepository.findById(movieDTO.getHallId());

        if (movieRepository.findById(movieId).isEmpty()) {
            throw new NoSuchElementException("Kein Film mit der ID " + movieId + " gefunden!");
        }

        checkIfMovieExists(movieDTO, movies);

        Hall hall = getHall(movieDTO, hallOptional);

        MovieVersion movieVersion = getMovieVersion(movieDTO);

        checkIfMovieVersionIsSupported(hall, movieVersion);

        Movie movie = movieRepository.findById(movieId).get();

        movie.setTitle(movieDTO.getTitle());
        movie.setMainCharacter(movieDTO.getMainCharacter());
        movie.setDescription(movieDTO.getDescription());
        movie.setPremieredAt(movieDTO.getPremieredAt());
        movie.setMovieVersion(movieVersion);

        movieRepository.save(movie);

        return new ResponseMovieDTO(
                movie.getId(),
                movie.getHall().getId(),
                movie.getTitle(),
                movie.getMainCharacter(),
                movie.getDescription(),
                movie.getPremieredAt(),
                movie.getMovieVersion()
        );
    }


    private static void checkIfMovieVersionIsSupported(Hall hall, MovieVersion movieVersion) {
        if (hall.getSupportedMovieVersion() != movieVersion) {
            throw new MovieVersionNotSupportedException("Der Saal mit der ID " + hall.getId() + " unterstützt nicht die Filmversion " + movieVersion);
        }
    }

    private static Hall getHall(MovieDTO movieDTO, Optional<Hall> hallOptional) {
        if (hallOptional.isEmpty()) {
            throw new HallNotFoundException("Kein Saal mit der ID " + movieDTO.getHallId() + " gefunden!");
        }

        return hallOptional.get();
    }

    private static void checkIfMovieExists(MovieDTO movieDTO, List<Movie> movies) {
        movies.forEach(movie -> {
            if (movie.getTitle().equalsIgnoreCase(movieDTO.getTitle())) {
                throw new EntityExistsException("Der Film mit dem Namen " + movieDTO.getTitle() + " ist bereits vorhanden.");
            }
        });
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
