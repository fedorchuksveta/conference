package com.conference.controller;

import com.conference.model.Presentation;
import com.conference.service.PresentationService;
import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/listener")
public class ListenerController {
    @Autowired
    private PresentationService presentationService;

    @Autowired
    private UserService userService;

    @GetMapping("/listenerHome")
    public String listener() {
        return "listenerHome";
    }

    @RequestMapping(value ="/presentationListener", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<Presentation> allPresentation = presentationService.findAll();
        model.addAttribute("presentations", allPresentation);
        return "presentationListener";
    }


    @RequestMapping(value = { "/goToPresentation" }, method = RequestMethod.GET)
    public String goToPresentation(Model model) {
        Presentation presentation = new Presentation();
        model.addAttribute("presentation", presentation);
        return "presentationListener";
    }

    @RequestMapping(value = { "/goPresentation/{id}" }, method = RequestMethod.GET)
    public String goPresentation(@PathVariable Long id) {

        userService.addListener(id);
        return "listenerHome";
    }


}
