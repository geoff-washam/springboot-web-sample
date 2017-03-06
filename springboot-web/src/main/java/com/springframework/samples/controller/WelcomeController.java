package com.springframework.samples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WelcomeController {

    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }
}
