package com.maven8919.movielister.service;

import com.maven8919.movielister.domain.MovieEntity;
import com.maven8919.movielister.repository.MovieRepository;
import com.maven8919.movielister.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieViewFromMovieEntityTransformerService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieView> getMovieViews() {
        List<MovieEntity> movies = movieRepository.findAll();
        return movies.stream()
                .filter(movie -> !movie.isWatched())
                .filter(movie -> movie.isWantToWatch())
                .map(movie -> convertMovieEntityToView(movie)).collect(Collectors.toList());
    }

    public List<MovieView> getMovieViews(List<MovieEntity> movies) {
        return movies.stream().map(movie -> convertMovieEntityToView(movie)).collect(Collectors.toList());
    }

    private MovieView convertMovieEntityToView(MovieEntity movie) {
        MovieView result = new MovieView();
        result.setTitle(movie.getTitle());
        result.setRating(movie.getRating());
        result.setNumberOfReviews(movie.getNumberOfReviews());
        result.setRottenTomatoesLink(movie.getRottenTomatoesLink());
        result.setWantToWatch(movie.isWantToWatch());
        result.setWatched(movie.isWatched());
        return result;
    }


}
