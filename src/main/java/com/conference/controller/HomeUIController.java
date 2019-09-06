package com.conference.controller;

import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class HomeUIController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView home() {
        System.out.println("mhgfkhgkhgkhgjhgjhgf");
        ModelAndView view = new ModelAndView("home");
        return view;
    }


}
