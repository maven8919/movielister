package com.maven8919.movielister;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.maven8919.movielister.domain.MovieEntity;
import com.maven8919.movielister.service.MovieRatingGetterService;
import com.maven8919.movielister.service.NcoreCrawlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovielisterApplicationTests {

    public static final String LA_LA_LAND_MOVIE_TITLE = "La la land";
    public static final String XXX_MOVIE_TITLE = "xXx: Return of Xander Cage";
    public static final String THE_SALESMAN_MOVIE_TITLE = "The Salesman (Forushand)";
    public static final String JOHN_WICK2_MOVIE_TITLE = "John Wick: Chapter 2";
    public static final String DOGS_PURPOSE_MOVIE_TITLE = "A Dog's Purpose";
    public static final int LA_LA_LAND_SCORE = 93;
    public static final String LA_LA_LAND_URL = "https://www.rottentomatoes.com/m/la_la_land/";
    public static final String XXX_URL = "https://www.rottentomatoes.com/m/xxx_return_of_xander_cage/";
    public static final String THE_SALESMAN_INCORRECT_URL = "https://www.rottentomatoes.com/m/the_salesman/";
    public static final String JOHN_WICK2_URL = "https://www.rottentomatoes.com/m/john_wick_chapter_2/";
    public static final String DOGS_PURPOSE_URL = "https://www.rottentomatoes.com/m/a_dogs_purpose/";
    public static final int XXX_SCORE = 43;
    public static final int SALESMAN_SCORE = 97;
    public static final int JOHN_WICK2_SCORE = 90;
    public static final int DOGS_PURPOSE_SCORE = 33;

    @Autowired
    private MovieRatingGetterService movieRatingGetterService;

    @Autowired
    private NcoreCrawlerService ncoreCrawlerService;

    @Test
    public void testConvertChars() {
        assertEquals("l", movieRatingGetterService.convertChar('l'));
        assertEquals("4", movieRatingGetterService.convertChar('4'));
        assertEquals("_", movieRatingGetterService.convertChar(' '));
        assertEquals("_", movieRatingGetterService.convertChar(':'));
        assertEquals("_", movieRatingGetterService.convertChar('!'));
        assertEquals("_", movieRatingGetterService.convertChar('?'));
        assertEquals("", movieRatingGetterService.convertChar('\''));
    }

    @Test
    public void testRemoveStuffBetweenParanthesesAndTrim() {
        assertEquals("The salesman", movieRatingGetterService.removeStuffBetweenParentheses("The salesman (forushande)"));
        assertEquals("Lofasz joska", movieRatingGetterService.removeStuffBetweenParentheses("   Lofasz(forushande) joska"));
        assertEquals("no parentheses", movieRatingGetterService.removeStuffBetweenParentheses("no parentheses"));
    }

    @Test
    public void testGetRottenTomatoesURLForLaLaLand() {
        assertEquals(LA_LA_LAND_URL, movieRatingGetterService.convertTitleToRottenTomatoesUrl(LA_LA_LAND_MOVIE_TITLE));
    }

    @Test
    public void testGetRottenTomatoesURLForXXXReturnOfXanderCage() {
        assertEquals(XXX_URL, movieRatingGetterService.convertTitleToRottenTomatoesUrl(XXX_MOVIE_TITLE));
    }

    @Test
    public void testGetRottenTomatoesURLForTheSalesman() {
        assertEquals(THE_SALESMAN_INCORRECT_URL, movieRatingGetterService.convertTitleToRottenTomatoesUrl(THE_SALESMAN_MOVIE_TITLE));
    }

    @Test
    public void testGetRottenTomatoesURLForJohnWickChapter2() {
        assertEquals(JOHN_WICK2_URL, movieRatingGetterService.convertTitleToRottenTomatoesUrl(JOHN_WICK2_MOVIE_TITLE));
    }

    @Test
    public void testGetRottenTomatoesURLForDogsPurpose() {
        assertEquals(DOGS_PURPOSE_URL, movieRatingGetterService.convertTitleToRottenTomatoesUrl(DOGS_PURPOSE_MOVIE_TITLE));
    }

    @Test
    public void testLaLaLandURLShouldBeLiveLink() {
        assertTrue(movieRatingGetterService.urlIsLive(LA_LA_LAND_URL));
    }

    @Test
    public void testXXXURLShouldBeLiveLink() {
        assertTrue(movieRatingGetterService.urlIsLive(XXX_URL));
    }

    @Test
    public void testTheSalesManURLShouldNotBeLiveLink() {
        assertFalse(movieRatingGetterService.urlIsLive(THE_SALESMAN_INCORRECT_URL));
    }

    @Test
    public void testJohnWick2URLShouldBeLiveLink() {
        assertTrue(movieRatingGetterService.urlIsLive(JOHN_WICK2_URL));
    }

    @Test
    public void testDogsPurposeURLShouldBeLiveLink() {
        assertTrue(movieRatingGetterService.urlIsLive(DOGS_PURPOSE_URL));
    }

    @Test
    public void testGetRatingShouldReturn93ForLaLaLand() {
        MovieEntity movie = movieRatingGetterService.getRating(LA_LA_LAND_MOVIE_TITLE);
        assertEquals(LA_LA_LAND_SCORE, movie.getRating());
        assertTrue(movie.isWantToWatch());
        assertFalse(movie.isWatched());
    }

    @Test
    public void testGetRatingShouldReturn43ForXXX() {
        MovieEntity movie = movieRatingGetterService.getRating(XXX_MOVIE_TITLE);
        assertEquals(XXX_SCORE, movie.getRating());
    }

    @Test
    public void testGetRatingShouldReturn98ForTheSalesman() {
        MovieEntity movie = movieRatingGetterService.getRating(THE_SALESMAN_MOVIE_TITLE);
        assertEquals(SALESMAN_SCORE, movie.getRating());
    }

    @Test
    public void testGetRatingShouldReturn90ForJohnWick2() {
        MovieEntity movie = movieRatingGetterService.getRating(JOHN_WICK2_MOVIE_TITLE);
        assertEquals(JOHN_WICK2_SCORE, movie.getRating());
    }

    @Test
    public void testGetRatingShouldReturn34ForDogsPurpose() {
        MovieEntity movie = movieRatingGetterService.getRating(DOGS_PURPOSE_MOVIE_TITLE);
        assertEquals(DOGS_PURPOSE_SCORE, movie.getRating());
    }

}
