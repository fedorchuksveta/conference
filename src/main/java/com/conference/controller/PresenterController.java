package com.conference.controller;

import com.conference.model.Presentation;
import com.conference.model.User;
import com.conference.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/presenter")
public class PresenterController {

    @Autowired
    PresentationService presentationService;


    @RequestMapping(value ="/presenterHome", method = RequestMethod.GET)
    public String presenter() {
        return "presenterHome";
    }

    @RequestMapping(value ="/presentationPresenter", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<Presentation> allPresentation = presentationService.findAll();
        model.addAttribute("presentations", allPresentation);
        return "presentationPresenter";
    }

    @RequestMapping(value = { "/addPresentation" }, method = RequestMethod.GET)
    public String registerUser(Model model) {
        Presentation presentation = new Presentation();
        model.addAttribute("presentation", presentation);
        return "presentationAdd";
    }

    @RequestMapping(value = { "/addPresentation" }, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("presentation") Presentation presentation) {
        presentationService.create(presentation);
        return "presentationSuccess";
    }

}
