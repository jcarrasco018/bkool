package com.bkool.infraestructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String health() {
        return "ok";
    }

}
