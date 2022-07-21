package com.spiralspree.wannajoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@EnableAutoConfiguration
@SpringBootApplication
public class Main {
    private final static Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        try {
            CustomLogger.setup("Logs.txt", new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Application started");
    }
}
