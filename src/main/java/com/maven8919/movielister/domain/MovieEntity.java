package com.maven8919.movielister.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private int rating;
    private int numberOfReviews;
    private String rottenTomatoesLink;
    private boolean wantToWatch;
    private boolean watched;

    public String getRottenTomatoesLink() {
        return rottenTomatoesLink;
    }

    public void setRottenTomatoesLink(String rottenTomatoesLink) {
        this.rottenTomatoesLink = rottenTomatoesLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isWantToWatch() {
        return wantToWatch;
    }

    public void setWantToWatch(boolean wantToWatch) {
        this.wantToWatch = wantToWatch;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

}
