package com.conference.controller;

import com.conference.model.Presentation;
import com.conference.model.User;
import com.conference.service.PresentationService;
import com.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
@RequestMapping("/")
public class HomeUIController {

    @Autowired
    UserService userService;

    @Autowired
    PresentationService presentationService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        List<Presentation> allPresentation = presentationService.findAll();
        model.addAttribute("presentations", allPresentation);
        return "home";
    }


    @GetMapping("/presentation")
    public String homePage(Model model) {
        List<Presentation> allPresentation = presentationService.findAll();
        model.addAttribute("presentations", allPresentation);
        return "presentation";
    }

//    @GetMapping("/dashboard")
//    public String dashboard(Model model) {
//        return "adminHome";
//    }



        @RequestMapping("/dashboard")
        public String index() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if (roles.contains("ADMIN"))
                return "redirect:/admin/adminHome";
            if (roles.contains("LISTENER"))
                return "redirect:/listener/listenerHome";
            if (roles.contains("PRESENTER"))
                return "redirect:/presenter/presenterHome";
            return "";
        }



}
