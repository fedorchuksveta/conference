package com.conference.controller;

import com.conference.model.Presentation;
import com.conference.model.User;
import com.conference.service.PresentationService;
import com.conference.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final PresentationService presentationService;

    public AdminController(PresentationService presentationService, UserService userService) {
        this.presentationService = presentationService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/adminHome"}, method = RequestMethod.GET)
    public String home() {
        System.out.println("hello");
        return "adminHome";
    }

    @GetMapping("/presentation")
    public String homePage(Model model) {
        List<Presentation> allPresentation = presentationService.findAll();
        model.addAttribute("presentations", allPresentation);
        return "presentation";
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

    @RequestMapping(path = {"/changeUser/{id}"})
    public String editUserById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            User user = userService.getOne(id.get());
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new User());
        }
        return "editUser";
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateUser(User user) {
        userService.createOrUpdateUser(user);
        return "adminHome";
    }

}
