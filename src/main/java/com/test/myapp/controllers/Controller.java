package com.test.myapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/request")
    public ResponseEntity request() {

        logger.info("Incoming request at {} for request /request ", applicationName);
        String response = restTemplate.getForObject("http://localhost:8090/response", String.class);
        return ResponseEntity.ok("response from /request + " + response);
    }

    @GetMapping("/response")
    public ResponseEntity response() {
        logger.info("Incoming request at {} at /response", applicationName);
        return ResponseEntity.ok("response from /response ");
    }
}