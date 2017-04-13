package com.maven8919.movielister.view;

public class MovieView {

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
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
}
