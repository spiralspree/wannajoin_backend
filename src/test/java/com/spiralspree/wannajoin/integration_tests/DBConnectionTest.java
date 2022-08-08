package com.spiralspree.wannajoin.integration_tests;

import com.spiralspree.wannajoin.CustomLogger;
import com.spiralspree.wannajoin.DataBaseManager;
import com.spiralspree.wannajoin.TestTableDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DBConnectionTest {

    private final static Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());
    private final static DataBaseManager dataBaseManager = new DataBaseManager();
    private final static DataSource dataSource = dataBaseManager.getDataSource();
    private final static TestTableDAO testTableDAO = new TestTableDAO(dataSource);
    private static String textToInsert;
    private static int retrievedID = -1;

    @BeforeAll
    @Order(1)
    static void setUpLogger() {
        try {
            CustomLogger.setup("DBConnectionTestLogs.txt", new SimpleFormatter());
            LOGGER.info("DBConnectionTest logger has been set up successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    @Order(2)
    static void insertTestRecordIntoDB() {
        String now = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        textToInsert = "Test record inserted at: " + now;
        try {
            retrievedID = testTableDAO.addTextToTestTable(textToInsert);
            if(retrievedID == -1){
                Assertions.fail("Acquiring ID of test record failed.");
            }
        } catch (SQLException e) {
            LOGGER.severe("DAO action failed.");
        }
    }

    @Test
    void retrievedTextEqualsToInsertionTimeWhenIdIsInsertionId() {
        String retrievedText;
        try {
            retrievedText = testTableDAO.getTextById(retrievedID);
            LOGGER.info("Query result: \"" + retrievedText + "\"");
            Assertions.assertEquals(textToInsert,retrievedText);
        } catch (SQLException e) {
            LOGGER.severe("DAO action failed.");
            Assertions.fail();
        }

    }

}
