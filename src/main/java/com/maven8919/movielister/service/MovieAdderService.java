package com.maven8919.movielister.service;

import com.maven8919.movielister.repository.MovieRepository;
import com.maven8919.movielister.domain.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieAdderService {

    @Autowired
    private MovieRepository movieRepository;

    public void saveMovie(MovieEntity movie) {
        movieRepository.save(movie);
    }

}
