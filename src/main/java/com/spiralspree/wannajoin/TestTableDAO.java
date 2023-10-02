package com.spiralspree.wannajoin;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class TestTableDAO {
    private final DataSource dataSource;
    private final static Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());

    public TestTableDAO (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTextById (int id) throws SQLException {
        LOGGER.info("DAO preparing request: getTextById");
        Connection con = dataSource.getConnection();
        String query = "SELECT text FROM test_table WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        con.close();
        if(!resultSet.next()){
            return null;
        }
        LOGGER.info("Record found.");
        return resultSet.getString(1);
    }

    public int addTextToTestTable(String textToAdd) throws SQLException {
        LOGGER.info("DAO preparing action: addText");
        String hostName = EnvUtility.getHostName();
        Connection con = dataSource.getConnection();
        String now = EnvUtility.getFormattedTimeDate();
        LOGGER.info(String.format("Host name: %s, time: %s", hostName, now));
        String sql = "INSERT INTO test_table (text) VALUES ('" + textToAdd + "') RETURNING id";
        Statement statement = con.createStatement();
        LOGGER.info("Executing query...");
        statement.execute(sql);
        LOGGER.info("Acquiring query result...");
        ResultSet insertResult = statement.getResultSet();
        if (insertResult.next()) {
            int newId = insertResult.getInt(1);
            if (newId != 0) {
                LOGGER.info("Insertion into database succeeded. Returned ID for new record is: " + newId);
                return newId;
            } else {
                LOGGER.severe("Acquired 0 as id number for new record. This is unintended behavior. Check query or database configuration!");
                throw new SQLException("Acquired 0 as id number. This is unintended behavior. Check query, or database configuration!");
            }
        } else {
            LOGGER.severe("Acquiring ID of new record in TestTable FAILED.");
            throw new SQLException("Acquiring ID of new record in TestTable FAILED.");
        }
    }
}
