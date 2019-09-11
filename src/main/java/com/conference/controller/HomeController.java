package com.conference.controller;

import com.conference.model.User;
import com.conference.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public User getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @GetMapping("all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping(produces = "application/json")
    public User save(@RequestBody User user){
        return userService.create(user);
    }

}
