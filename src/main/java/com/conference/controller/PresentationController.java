package com.conference.controller;

import com.conference.model.Presentation;
import com.conference.model.User;
import com.conference.service.PresentationService;
import com.conference.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pr")
@Slf4j
public class PresentationController {
    @Autowired
    PresentationService presentationService;

    @GetMapping("{id}")
    public Presentation getOne(@PathVariable Long id) {
        return presentationService.getOne(id);
    }

    @GetMapping("all")
    public List<Presentation> findAll() {
        return presentationService.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        presentationService.delete(id);
    }

    @PostMapping
    public Presentation save(@RequestBody Presentation presentation){
        return presentationService.create(presentation);
    }

}
