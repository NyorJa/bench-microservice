package com.bench.practice.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Value("${test.message:helloxxxx}")
    private String message;

    @GetMapping
    public String greet() {
        return message;
    }
}
