import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class patientlogindatabase {
    static final String URL = "jdbc:mysql://localhost:3306/patientlogin";
    static final String USER = "root";
    static final String PASS = "honey@053";

    public user validateUser(String emailfnt, String passwordfnt) throws SQLException {
        user u1 = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            String query = "SELECT * FROM patientlogindb WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, emailfnt);
            stmt.setString(2, passwordfnt);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u1 = new user();
                u1.setEmail(rs.getString("email"));
                u1.setPassword(rs.getString("password"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found!");
        }

        return u1;
    }
}
