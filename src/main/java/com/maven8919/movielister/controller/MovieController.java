package com.maven8919.movielister.controller;

import com.maven8919.movielister.domain.MovieEntity;
import com.maven8919.movielister.service.MovieViewFromMovieEntityTransformerService;
import com.maven8919.movielister.service.MovieWantToWatchService;
import com.maven8919.movielister.service.MovieWatchedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired private MovieViewFromMovieEntityTransformerService movieViewFromMovieEntityTransformerService;
    @Autowired private MovieWantToWatchService movieWantToWatchService;
    @Autowired private MovieWatchedService movieWatchedService;

    @RequestMapping(value = "/")
    public String movies(Model model) {
        model.addAttribute("movies", movieViewFromMovieEntityTransformerService.getMovieViews());
        return "movies";
    }

    @RequestMapping(value = "/movies/wantToWatch/")
    public String moviesWantToWatch(Model model, @RequestParam("title") String title) {
        movieWantToWatchService.wantToWatch(title);
        return "wantToWatch";
    }

    @RequestMapping(value = "/movies/dontWantToWatch/{title}")
    public String moviesDontWantToWatch(Model model, @PathVariable("title") String title) {
        movieWantToWatchService.dontWantToWatch(title);
        return "dontWantToWatch";
    }

    @RequestMapping(value = "/movies/watchedMovie/{title}")
    public String watched(Model model, @PathVariable("title") String title) {
        movieWatchedService.watched(title);
        return "watched";
    }

    @RequestMapping(value = "/movies/watched/")
    public String watchedMovies(Model model) {
        List<MovieEntity> watchedMovies = movieWatchedService.getWatchedMovies(true);
        model.addAttribute("movies", movieViewFromMovieEntityTransformerService.getMovieViews(watchedMovies));
        return "watched";
    }

    @RequestMapping(value = "/movies/dontWantToWatch")
    public String moviesDontWantToWatchView(Model model) {
        List<MovieEntity> dontWantToWatchMovies = movieWantToWatchService.dontWantToWatchMovies();
        model.addAttribute("movies", movieViewFromMovieEntityTransformerService.getMovieViews(dontWantToWatchMovies));
        return "dontWantToWatch";
    }

}
