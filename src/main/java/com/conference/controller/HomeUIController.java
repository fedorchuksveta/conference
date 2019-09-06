package com.conference.controller;

import com.conference.model.User;
import com.conference.service.PresentationService;
import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
//@RequestMapping("/")
public class HomeUIController {

    @Autowired
    UserService userService;

    @Autowired
    PresentationService presentationService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
//
//    @GetMapping("/presentation")
//    public String viewPresentation(){
//        return "roomPresentation";
//    }

    @GetMapping("/presentation/")
    public String homePage(Model model) {
        model.addAttribute("presentation", presentationService.findAll());
        return "presentation";
    }


    @RequestMapping("/register/")
    public String registerCustomer(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "registerUser";

    }

}
