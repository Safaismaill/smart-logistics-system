package com.safaismail.smartlogistics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "Smart Logistics API is running";
    }

}