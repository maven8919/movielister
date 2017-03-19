package com.maven8919.movielister.service;

import com.maven8919.movielister.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieListUpdaterService {

    public static final int MINIMUM_MOVIE_RATING = 80;
    @Autowired
    private NcoreCrawlerService ncoreCrawlerService;

    @Autowired
    private MovieNameFromImdbLinkService movieNameFromImdbLinkService;

    @Autowired
    private MovieRatingGetterService movieRatingGetterService;

    @Autowired
    private MovieRepository movieRepository;

    //@Scheduled(fixedRate = 20000)
    public void insertNewMoviesToDb() {
        List<String> imdbLinks = ncoreCrawlerService.getImdbLinks();
        imdbLinks.stream()
                .map(movieNameFromImdbLinkService::getMovieNameFromImdbLine)
                .filter(movieName -> !"".equals(movieName))
                .map(title -> movieRatingGetterService.getRating(title))
                .filter(movie -> movie.getRating() >= MINIMUM_MOVIE_RATING &&
                        movieRepository.findByTitle(movie.getTitle()).size() == 0)
                .forEach(movieRepository::save);
    }
}