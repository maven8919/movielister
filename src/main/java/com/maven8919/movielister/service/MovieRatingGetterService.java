package com.maven8919.movielister.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.maven8919.movielister.domain.MovieEntity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
public class MovieRatingGetterService {

    private static final String URL_PREFIX = "https://www.rottentomatoes.com/m/";
    public static final String DUCK_DUCK_GO_SEARCH_PREFIX = "https://duckduckgo.com/?q=";
    public static final String ROTTEN_TOMATOES_SUFFIX = " rotten tomatoes";

    public MovieEntity getRating(String title) {
        MovieEntity result = new MovieEntity();
        result.setTitle(title);
        result.setRating(0);
        result.setWantToWatch(true);
        result.setWatched(false);
        String rottenTomatoesUrl = convertTitleToRottenTomatoesUrl(title);
        if (!urlIsLive(rottenTomatoesUrl)) {
            rottenTomatoesUrl = getRottenTomatoesUrlThroughDuckDuckGo(title);
        }
        int rating = getRatingFromUrl(rottenTomatoesUrl);
        result.setRating(rating);
        result.setRottenTomatoesLink(rottenTomatoesUrl);
        return result;
    }

    private String getRottenTomatoesUrlThroughDuckDuckGo(String title) {
        String result = "";
        String searchUrl = DUCK_DUCK_GO_SEARCH_PREFIX + title + ROTTEN_TOMATOES_SUFFIX;
        try (WebClient webClient = new WebClient()) {
            HtmlPage page = webClient.getPage(searchUrl);
            DomElement firstResult = page.getElementById("r1-0");
            result = firstResult.getElementsByTagName("a").get(0).getAttribute("href");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getRatingFromUrl(String rottenTomatoesUrl) {
        int result = 0;
        try {
            Document doc = Jsoup.connect(rottenTomatoesUrl).get();
            Elements scoreSpan = doc.select(".meter-value.superPageFontColor");
            String ratingPercentage = scoreSpan.get(0).text();
            result = Integer.parseInt(scratchPercentageSign(ratingPercentage));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String scratchPercentageSign(String string) {
        return string.replaceAll("%", "");
    }

    public String convertTitleToRottenTomatoesUrl(String title) {
        StringBuffer sb = new StringBuffer();
        String titleWoParantheses = removeStuffBetweenParentheses(title);
        titleWoParantheses.toLowerCase()
                .chars()
                .mapToObj(i -> (char) i)
                .forEach(ch -> sb.append(convertChar(ch)));
        return URL_PREFIX + replaceMultipleUnderscores(sb.toString()) + "/";
    }

    public String convertChar(Character ch) {
        String result = ch.toString();
        if (!Character.isLetterOrDigit(ch) && ch != '\'') {
            result = "_";
        }
        if (ch == '\'') {
            result = "";
        }
        return result;
    }

    public String removeStuffBetweenParentheses(String title) {
        return title.replaceAll("\\(.*?\\)","").trim();
    }

    private String replaceMultipleUnderscores(String title) {
        return title.replaceAll("_{2,}", "_");
    }

    public boolean urlIsLive(String url) {
        boolean result = false;
        try {
            Connection.Response response = Jsoup.connect(url).execute();
            if (200 == response.statusCode()) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
