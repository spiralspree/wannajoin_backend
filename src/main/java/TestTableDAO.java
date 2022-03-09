import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTableDAO {
    private final DataSource dataSource;

    public TestTableDAO (DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public String getTextById (int id) throws SQLException {
        System.out.println("DAO preparing request: getTextById");
        Connection con = dataSource.getConnection();
        String query = "SELECT text FROM test_table WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){
            return null;
        }
        System.out.println("Record found.");
        return resultSet.getString(1);
    }
}
