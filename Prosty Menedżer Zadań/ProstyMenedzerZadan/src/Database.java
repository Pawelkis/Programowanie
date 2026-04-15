import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connect() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:tasks.db");
    }
}