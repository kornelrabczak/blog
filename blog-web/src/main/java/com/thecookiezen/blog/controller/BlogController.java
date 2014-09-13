package com.thecookiezen.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

    @RequestMapping("/")
    String index() {
        return "index";
    }

}
