package com.maven8919.movielister.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NcoreCrawlerService {

    private static final String NCORE_HD_MOVIES_LINK = "https://ncore.cc/torrents.php?tipus=hd";
    private static final String LOGIN_FORM_NAME = "login";
    private static final String SUBMIT_BUTTON_NAME = "submit";
    private static final String USERNAME_FIELD_NAME = "nev";
    private static final String PASSWORD_FIELD_NAME = "pass";

    @Value("${ncore.username}")
    private String ncoreUsername;

    @Value("${ncore.password}")
    private String ncorePassword;


    public List<String> getImdbLinks() {
        List<String> result = new ArrayList<>();
        WebClient webClient = new WebClient();
        try {
            HtmlPage loginPage = webClient.getPage(NCORE_HD_MOVIES_LINK);
            HtmlForm form = loginPage.getFormByName(LOGIN_FORM_NAME);
            HtmlSubmitInput submitButton =  form.getInputByName(SUBMIT_BUTTON_NAME);
            HtmlTextInput usernameField =  form.getInputByName(USERNAME_FIELD_NAME);
            HtmlPasswordInput passwordField =  form.getInputByName(PASSWORD_FIELD_NAME);
            usernameField.setValueAttribute(ncoreUsername);
            passwordField.setValueAttribute(ncorePassword);
            HtmlPage hdMoviesPage = submitButton.click();
            List<HtmlAnchor> anchors = hdMoviesPage.getAnchors();
            result = anchors.stream()
                    .filter(anchor -> anchor.getAttribute("href").contains("dereferer"))
                    .map(anchor -> anchor.getAttribute("href").substring(22))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
