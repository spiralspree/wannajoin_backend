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
        String hostname = "Unknown";
        try
        {
            InetAddress addr;
            LOGGER.info("Trying to access host name...");
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (Exception ex)
        {
            LOGGER.warning("Could not access host name.");
        }
        Connection con = dataSource.getConnection();
        String now = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        LOGGER.info(String.format("Host name: %s, time: %s", hostname, now));
        String sql = "INSERT INTO test_table (text) VALUES ('" + textToAdd + "') RETURNING id";
        Statement statement = con.createStatement();
        LOGGER.info("Executing query...");
        statement.execute(sql);
        LOGGER.info("Acquiring query result...");
        ResultSet insertResult = statement.getResultSet();
        if(insertResult.next()) {
            int newId = insertResult.getInt(1);
            LOGGER.info("Insertion into database succeeded. Returned ID for new record is: " + newId);
            return newId;
        } else {
            LOGGER.severe("Acquiring ID of new record in TestTable FAILED.");
            throw new SQLException("Could not acquire ID of new record in TestTable.");
        }
    }
}
