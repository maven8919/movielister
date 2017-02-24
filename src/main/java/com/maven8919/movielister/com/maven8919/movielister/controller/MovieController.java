package com.maven8919.movielister.com.maven8919.movielister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

    @RequestMapping(value = "/")
    public String movies() {
        return "movies";
    }

}
