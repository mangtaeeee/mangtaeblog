package com.mangtaeblog.api.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostViewController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "하위");
        return "hello";
    }
}
