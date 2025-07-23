import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    static final String URL = "jdbc:mysql://localhost:3306/doctorlogin";  // Ensure database name is correct
    static final String USER = "root";
    static final String PASS = "honey@053";

    public user validateUser(String emailfnt, String passwordfnt) throws SQLException {
        user u1 = null;

        try {
            // 🔹 Load MySQL JDBC Driver (Fixes ClassNotFoundException)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 🔹 Establish connection
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            // 🔹 Prepare statement
            String query = "SELECT * FROM doctorlogindb WHERE email=? AND password=?";
            																		// Check correct table name
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, emailfnt);
            stmt.setString(2, passwordfnt);

            // 🔹 Execute query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u1 = new user();
                u1.setEmail(rs.getString("email"));
                u1.setPassword(rs.getString("password"));
            }

            // 🔹 Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found!");
        }

        return u1;  // Returns null if user not found
    }
}

