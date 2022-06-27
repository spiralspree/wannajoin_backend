package com.spiralspree.wannajoin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("")
    public String getHome(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "https://sharp-carson-94751d.netlify.app");
        return "Hello from TestController";
    }
}
