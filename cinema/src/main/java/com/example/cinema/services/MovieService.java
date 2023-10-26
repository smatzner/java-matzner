package com.example.cinema.services;

import com.example.cinema.dtos.MovieDTO;
import com.example.cinema.dtos.ResponseMovieDTO;
import com.example.cinema.dtos.UpdateMovieDTO;
import com.example.cinema.entities.Hall;
import com.example.cinema.entities.Movie;
import com.example.cinema.exceptions.HallNotFoundException;
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

    public ResponseMovieDTO createMovie(MovieDTO movieDTO){
        List<Movie> movies = movieRepository.findAll();
        Optional<Hall> hallOptional = hallRepository.findById(movieDTO.getHallId());

        movies.forEach(movie -> {
            if(movie.getTitle().equalsIgnoreCase(movieDTO.getTitle())){
                throw new EntityExistsException("Der Film mit dem Namen " + movieDTO.getTitle() + " ist bereits vorhanden.");
            }
        });

        if(hallOptional.isEmpty()) {
            throw new HallNotFoundException("Kein Saal mit der ID " + movieDTO.getHallId() + " gefunden!");
        }

        Hall hall = hallOptional.get();

        if(!hall.getSupportedMovieVersion().equals(movieDTO.getMovieVersion())){
            throw new MovieVersionNotSupportedException("Der Saal mit der ID " + hall.getId() + " unterstützt nicht die Filmversion " + movieDTO.getMovieVersion());
        }

        Movie movie = Movie.builder()
                .hall(hall)
                .title(movieDTO.getTitle())
                .mainCharacter(movieDTO.getMainCharacter())
                .description(movieDTO.getDescription())
                .premieredAt(movieDTO.getPremieredAt())
                .movieVersion(movieDTO.getMovieVersion())
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

    public ResponseMovieDTO updateMovie(int movieId, UpdateMovieDTO updateMovieDTO){
        if(movieRepository.findById(movieId).isEmpty()){
            throw new NoSuchElementException("Kein Film mit der ID " + movieId + " gefunden!");
        }

        Movie movie = movieRepository.findById(movieId).get();

        movie.setTitle(updateMovieDTO.getTitle());
        movie.setMainCharacter(updateMovieDTO.getMainCharacter());
        movie.setDescription(updateMovieDTO.getDescription());
        movie.setPremieredAt(updateMovieDTO.getPremieredAt());
        movie.setMovieVersion(updateMovieDTO.getMovieVersion());

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
}
