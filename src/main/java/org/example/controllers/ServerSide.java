package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerSide {
    @GetMapping("/hello")
    String hello() {
        return "hello world!";
    }

    @GetMapping("/hello/{who}")
    String helloYou(@PathVariable String who) {
        return "hello " + who + "!";
    }
}
