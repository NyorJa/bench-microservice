package com.bench.practice.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Value("${test.message:helloxxxx}")
    private String message;

    @GetMapping
    public String greet() {
        return message;
    }
}
