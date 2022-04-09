import javax.sql.DataSource;
import java.net.InetAddress;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    public void addTextToTestTable() throws SQLException {
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
        String sql = "INSERT INTO test_table (text) VALUES ('This text was added by the application at " + now + " by " + hostname + ".')";
        con.createStatement().executeUpdate(sql);
        LOGGER.info("Insert into database succeeded.");
    }
}
