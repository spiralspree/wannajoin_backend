package com.spiralspree.wannajoin;

import com.spiralspree.wannajoin.models.HbrntTestClass;
import com.spiralspree.wannajoin.models.repositories.HbrntTestClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private HbrntTestClassRepo repo;

    @CrossOrigin(origins = "https://sharp-carson-94751d.netlify.app/")
    @GetMapping("")
    public String getHome() {
        HbrntTestClass testClass = new HbrntTestClass();
        String hostName = EnvUtility.getHostName();
        String now = EnvUtility.getFormattedTimeDate();
        testClass.setText("Record by JpaRepository from host: " + hostName + " at: " + now);
        repo.save(testClass);
        return "Hello from TestController";
    }
}
