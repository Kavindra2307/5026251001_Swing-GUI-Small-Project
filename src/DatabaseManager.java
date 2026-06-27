import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/game_project?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "bn_processmaker";
    private static final String PASSWORD = "882f53c8a9";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
