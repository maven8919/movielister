package com.maven8919.movielister.service;

import com.maven8919.movielister.domain.MovieEntity;
import com.maven8919.movielister.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieWantToWatchService {

    @Autowired
    private MovieRepository movieRepository;

    public void wantToWatch(String title) {
        List<MovieEntity> movieEntitys = movieRepository.findByTitle(title);
        for (MovieEntity movie : movieEntitys) {
            movie.setWantToWatch(true);
            movieRepository.saveAndFlush(movie);
        }
    }

    public void dontWantToWatch(String title) {
        List<MovieEntity> movieEntitys = movieRepository.findByTitle(title);
        for (MovieEntity movie : movieEntitys) {
            movie.setWantToWatch(false);
            movieRepository.saveAndFlush(movie);
        }
    }

    public List<MovieEntity> dontWantToWatchMovies() {
        return movieRepository.findAll().stream().filter(movie -> !movie.isWantToWatch()).collect(Collectors.toList());
    }

}
