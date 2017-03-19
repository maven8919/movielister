package com.maven8919.movielister;

import com.maven8919.movielister.service.MovieNameFromImdbLinkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieNameFromImdbLinkServiceTest {

    private static final String ASSASINS_CREED_IMDB_LINK_WITH_SLASH = "http://www.imdb.com/title/tt2094766/";
    private static final String ASSASINS_CREED_IMDB_LINK_WO_SLASH = "http://www.imdb.com/title/tt2094766";
    private static final String INVALID_LINK = "invalid_link";

    @Autowired
    private MovieNameFromImdbLinkService movieNameFromImdbLinkService;

    @Test
    public void testAssasinsCreedImdbLinkShouldReturnAssasinsCreed() {
        assertEquals("Assassin's Creed", movieNameFromImdbLinkService.getMovieNameFromImdbLine(ASSASINS_CREED_IMDB_LINK_WITH_SLASH));
        assertEquals("Assassin's Creed", movieNameFromImdbLinkService.getMovieNameFromImdbLine(ASSASINS_CREED_IMDB_LINK_WO_SLASH));
        assertEquals("", movieNameFromImdbLinkService.getMovieNameFromImdbLine(INVALID_LINK));
    }
}