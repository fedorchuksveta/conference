package com.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listener")
public class ListenerController {

    @GetMapping("/listenerHome")
    public String listener() {
        return "listenerHome";
    }
}
