package com.conference.controller;

import com.conference.dto.PresentationDto;
import com.conference.model.Presentation;
import com.conference.service.PresentationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/presenter")
public class PresenterController {

    private final PresentationService presentationService;

    public PresenterController(PresentationService presentationService) {
        this.presentationService = presentationService;
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
                                   @ModelAttribute("presentation") PresentationDto presentationDto){
//                                   BindingResult result) {
//        if (result.hasErrors()) {
//            return "redirect:/presenter/addPresentation";
//        }
//        if (!presentationService.validateDateTime(presentationDto)){
//            result.addError(new ObjectError("start", "Incorrect time"));
//            return "redirect:/presenter/addPresentation";
//        }
        presentationService.savePresentation(presentationDto);
        return "presenterHome";
    }


//    @RequestMapping(value = {"/deletePresentationPresenter"}, method = RequestMethod.GET)
//    public String pagePresenterDeletePresentation(Model model) {
//        List<Presentation> presentations = presentationService.findAll();
//        model.addAttribute("presentations", presentations);
//        return "PresentationPresenter";
//    }
//
//    @RequestMapping(value = "/deletePresentationPresenter/{id}", method = RequestMethod.GET)
//    public String pagePresenterDeletePresentation(@PathVariable Long id) {
//        presentationService.delete(id);
//        return "presenterHome";
//    }
//
//    @RequestMapping(path = {"/changePresentation/{id}"})
//    public String editPresenterById(Model model, @PathVariable("id") Optional<Long> id) {
//        if (id.isPresent()) {
//            Presentation presentation = presentationService.getOne(id.get());
//            model.addAttribute("presentation", presentation);
//        } else {
//            model.addAttribute("presentation", new Presentation());
//        }
//        return "editPresentation";
//    }

}
