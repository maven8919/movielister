package com.maven8919.movielister.com.maven8919.movielister.controller;

import com.maven8919.movielister.com.maven8919.movielister.domain.MovieEntity;
import com.maven8919.movielister.com.maven8919.movielister.service.MovieAdderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

    @Autowired
    private MovieAdderService movieAdderService;

    @RequestMapping(value = "/")
    public String movies() {
        MovieEntity movie = new MovieEntity();
        movie.setTitle("proba");
        movie.setRating(22);
        movie.setWantToWatch(false);
        movie.setWatched(true);
        movieAdderService.saveMovie(movie);
        return "movies";
    }

}
