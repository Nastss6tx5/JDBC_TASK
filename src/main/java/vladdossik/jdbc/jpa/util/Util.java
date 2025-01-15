package vladdossik.jdbc.jpa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres?preferQueryMode=extended";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isValid(5)) {
                connection.close();
                throw new SQLException("Connection is invalid");
            }
            return connection;
        } catch (SQLException e) {
            throw new SQLException("Failed to get connection", e);
        }
    }
}
