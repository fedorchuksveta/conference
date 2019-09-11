package com.conference.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String sayHello() {
        String datatime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd - MMMM - yyyy -- HH:mm:ss"));
        return "Rest Service is now available     " +
                "IT`S WORKING. Now is : ___  " +
                datatime;
    }
}
