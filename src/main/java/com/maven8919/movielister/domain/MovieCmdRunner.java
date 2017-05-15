package com.maven8919.movielister.domain;

import com.maven8919.movielister.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MovieCmdRunner implements CommandLineRunner{

    @Autowired private MovieRepository movieRepository;

    @Override
    public void run(String... strings) throws Exception {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1l);
        movieEntity.setTitle("Valami");
        movieEntity.setNumberOfReviews(92);
        movieEntity.setRating(94);
        movieEntity.setRottenTomatoesLink("asdfa");
        movieEntity.setWatched(false);
        movieEntity.setWantToWatch(true);
        movieRepository.save(movieEntity);
    }
}
