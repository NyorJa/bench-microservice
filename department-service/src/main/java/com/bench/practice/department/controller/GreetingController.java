package com.bench.practice.department.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class GreetingController {

    @Value("${greeting:hello-world}")
    private String greeting;

    @GetMapping
    public String  greet() {
        return greeting;
    }
}
