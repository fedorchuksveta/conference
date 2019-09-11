package com.conference.controller;

import com.conference.model.User;
import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginPage")
    public String loginUser() {
        return "loginPage";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registerUser";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String savePerson(@Valid User user, BindingResult bindingResult, Model model) {
        Optional<User> userExists = userService.findUserByEmail(user.getEmail());
        if (userExists.isPresent()) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (!bindingResult.hasErrors()) {
            user = userService.create(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", user);
            return "registerUserSuccess";
        }
        return "registerUserSuccess";
    }

//    @GetMapping("/admin/home")
//    public String home(Model model) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findByUserName(auth)..findUserByEmail(auth.getName()).get();
//        model.addAttribute("userName", "Welcome " + user.getFirstname() + " " + user.getLastname() + " (" + user.getEmail() + ")");
//        model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
//
//        return "admin/home";
//    }

}
