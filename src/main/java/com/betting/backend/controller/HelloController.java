package com.betting.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Backend is running!";
    }

    @GetMapping("/api")
    public String hello() {
        return "Hello from the API!";
    }
    @GetMapping("/api/ping")
    public String ping() {
        return "pong";
}
}
