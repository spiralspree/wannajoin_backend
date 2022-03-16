import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
