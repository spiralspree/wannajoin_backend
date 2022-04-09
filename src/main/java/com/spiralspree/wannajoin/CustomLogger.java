package com.spiralspree.wannajoin;

import java.io.IOException;
import java.util.logging.*;
import java.util.logging.Logger;

public class CustomLogger {

    public static void setup(String fileNameText, Formatter chosenFormatter) throws IOException {
        Logger logger = Logger.getLogger(CustomLogger.class.getName());
        logger.setLevel(Level.INFO);

        FileHandler handler = new FileHandler(fileNameText);
        handler.setFormatter(chosenFormatter);
        logger.addHandler(handler);
    }
}
