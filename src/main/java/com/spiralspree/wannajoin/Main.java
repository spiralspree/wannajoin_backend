package com.spiralspree.wannajoin;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private final static Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());

    public static void main(String[] args) {
        try {
            CustomLogger.setup("Logs.txt", new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Application started");

        DataBaseManager dataBaseManager = new DataBaseManager();
        DataSource dataSource = dataBaseManager.getDataSource();
        TestTableDAO testTableDAO = new TestTableDAO(dataSource);

        String retrievedText;
        try {
            retrievedText = testTableDAO.getTextById(1);
            LOGGER.info("Query result: \"" + retrievedText + "\"");
        } catch (SQLException e) {
            LOGGER.severe("DAO action failed.");
        }

        try {
            testTableDAO.addTextToTestTable();
        } catch (SQLException e) {
            LOGGER.severe("DAO action failed.");
        }
    }
}
