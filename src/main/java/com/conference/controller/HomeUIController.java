package com.conference.controller;

import com.conference.model.Presentation;
import com.conference.model.User;
import com.conference.service.PresentationService;
import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeUIController {

    @Autowired
    UserService userService;

    @Autowired
    PresentationService presentationService;

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }

//    @GetMapping("/presentation")
//    public String viewPresentation(){
//        return "roomPresentation";
//    }

    @GetMapping("/presentation")
    public String homePage(Model model) {


        List<Presentation> allPresentation = presentationService.findAll();
        System.out.println(allPresentation);
        model.addAttribute("presentations", allPresentation);

        return "presentation";
    }

//    @GetMapping("/presentation")
//    public String presentation() {
//        return "presentation";
//    }

//    @RequestMapping("/register")
//    public String registerCustomer(Model model) {
//
//        User user = new User();
//        model.addAttribute("user", user);
//
//        return "registerUser";
//
//    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String registerUserPost(@Valid @ModelAttribute("user") User user, BindingResult result,
//                                   Model model) {
//
//        if (result.hasErrors()) {
//            return "registerUser";
//        }
//
//        userService.create(user);
//
//        return "registerUserSuccess";
//
//    }
//
//    @RequestMapping(value = "/loginPage")
//    public String loginUser() {
//
//        return "loginPage";
//
//    }


    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String registerUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "registerUser";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("user") User user) {

        userService.create(user);

        return "registerUserSuccess";

    }
}
