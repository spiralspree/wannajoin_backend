package com.spiralspree.wannajoin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class TestController {

    @CrossOrigin(origins = "https://sharp-carson-94751d.netlify.app/")
    @GetMapping("")
    public String getHome() {
        return "Hello from TestController";
    }
}
