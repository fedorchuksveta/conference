package com.conference.controller;


import com.conference.model.User;
import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/adminHome"}, method = RequestMethod.GET)
    public String home() {
        return "adminHome";
    }

    @RequestMapping(value = {"/deleteUser"}, method = RequestMethod.GET)
    public String pageAdminDeleteUser(Model model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "deleteUser";

    }


    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "adminHome";
    }



}
