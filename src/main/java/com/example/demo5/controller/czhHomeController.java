package com.example.demo5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 */
@Controller
public class czhHomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/user/login";
    }
}
