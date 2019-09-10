package com.conference.controller;

import com.conference.dto.PresentationDto;
import com.conference.model.Presentation;
import com.conference.service.PresentationService;
import com.conference.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/presenter")
public class PresenterController {

    private final PresentationService presentationService;
    private final ScheduleService scheduleService;

    public PresenterController(PresentationService presentationService, ScheduleService scheduleService1) {
        this.presentationService = presentationService;
        this.scheduleService = scheduleService1;
    }

    @RequestMapping(value = "/presenterHome", method = RequestMethod.GET)
    public String presenter() {
        return "presenterHome";
    }

    @RequestMapping(value = "/presentationPresenter", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<Presentation> allPresentation = presentationService.findAll();
        model.addAttribute("presentations", allPresentation);
        return "presentationPresenter";
    }

    @RequestMapping(value = {"/addPresentation"}, method = RequestMethod.GET)
    public String addPresentation(Model model) {
        PresentationDto presentation = new PresentationDto();
        model.addAttribute("presentation", presentation);
        return "presentationAdd";
    }

    @RequestMapping(value = {"/addPresentation"}, method = RequestMethod.POST)
    public String savePresentation(Model model,
                                   @ModelAttribute("presentation") PresentationDto presentationDto) {
        scheduleService.savePresentation(presentationDto);
        return "presentationSuccess";
    }

}
