package com.jp.fix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.fix.FixApplication;

@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    public String home() {
        return "Admin version : " + FixApplication.appVersion;
    }

    @GetMapping("start")
    public String start() {
        return "Starting...";
    }
}
