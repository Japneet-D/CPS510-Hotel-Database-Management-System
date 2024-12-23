import java.sql.*;

public class DBConnection {
    // Change the URL, USER, and PASSWORD as per your database settings
    // Actual values are hidden as this is part of public repositories
    private static final String URL = "jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl";
    private static final String USER = "******";  // Actual DB username is hidden
    private static final String PASSWORD = "******";  // Actual DB password is hiddden

    // Establish and return a connection
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  // Oracle JDBC driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Close the connection
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
