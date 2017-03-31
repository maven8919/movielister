package com.maven8919.movielister.controller;

import com.maven8919.movielister.service.MovieListUpdaterService;
import com.maven8919.movielister.service.MovieViewFromMovieEntityTransformerService;
import com.maven8919.movielister.service.NcoreCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

    @Autowired
    private MovieViewFromMovieEntityTransformerService movieViewFromMovieEntityTransformerService;

    @Autowired
    private MovieListUpdaterService movieListUpdaterService;

    @RequestMapping(value = "/")
    public String movies(Model model) {
        model.addAttribute("movies", movieViewFromMovieEntityTransformerService.getMovieViews());
        return "movies";
    }

}
