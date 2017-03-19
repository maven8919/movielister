package com.maven8919.movielister.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MovieNameFromImdbLinkService {


    public String getMovieNameFromImdbLine(String imdbLink) {
        String result = "";
        WebClient webClient = setupWebClient();
        try {
            HtmlPage imdbPage = webClient.getPage(imdbLink);
            HtmlElement titleDiv = imdbPage.getFirstByXPath("//div[contains(@class,'title_wrapper')]");
            HtmlElement h1Element = titleDiv.getElementsByTagName("h1").get(0);
            h1Element.removeChild("span", 0);
            result = h1Element.getTextContent().replaceAll("\u00A0", "").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private WebClient setupWebClient() {
        WebClient result = new WebClient();
        result.getOptions().setJavaScriptEnabled(false);
        result.getOptions().setUseInsecureSSL(true);
        return result;
    }

}