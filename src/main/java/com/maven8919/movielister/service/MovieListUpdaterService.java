package com.maven8919.movielister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieListUpdaterService {

    @Autowired
    private NcoreCrawlerService ncoreCrawlerService;

    @Autowired
    private MovieNameFromImdbLinkService movieNameFromImdbLinkService;



    //@Scheduled(fixedRate = 20000)
    public void insertNewMoviesToDb() {
        List<String> imdbLinks = ncoreCrawlerService.getImdbLinks();
        imdbLinks.stream()
                .map(movieNameFromImdbLinkService::getMovieNameFromImdbLine)
                .filter(movieName -> !"".equals(movieName));
    }
}