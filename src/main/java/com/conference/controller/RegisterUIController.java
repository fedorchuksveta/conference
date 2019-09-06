package com.conference.controller;


import com.conference.model.Presentation;
import com.conference.model.Room;
import com.conference.model.User;
import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterUIController {

    @Autowired
    UserService userService;


    @RequestMapping("/register")
    public String registerCustomer(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "registerUser";

    }
}