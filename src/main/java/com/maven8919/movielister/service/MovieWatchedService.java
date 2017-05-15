package com.maven8919.movielister.service;

import com.maven8919.movielister.domain.MovieEntity;
import com.maven8919.movielister.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieWatchedService {

    @Autowired private MovieRepository movieRepository;

    public void watched(String title) {
        List<MovieEntity> movies = movieRepository.findByTitle(title);
        for (MovieEntity movie : movies) {
            movie.setWatched(true);
            movieRepository.saveAndFlush(movie);
        }
    }

    public List<MovieEntity> getWatchedMovies(boolean watched) {
        return movieRepository.findByWatched(watched);
    }
}
