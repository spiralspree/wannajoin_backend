import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        DataBaseManager dataBaseManager = new DataBaseManager();
        DataSource dataSource = dataBaseManager.getDataSource();
        TestTableDAO testTableDAO = new TestTableDAO(dataSource);
        try {
            System.out.println("Query result: \"" + testTableDAO.getTextById(1) + "\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
