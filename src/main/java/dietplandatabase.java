import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dietplandatabase {
    static final String URL = "jdbc:mysql://localhost:3306/dietplan";
    static final String USER = "root";
    static final String PASS = "honey@053";

    public List<dietplan> getDietPlan() throws SQLException {
        List<dietplan> dietPlans = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            String query = "SELECT day, meal, foodItems FROM dietplandb";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dietplan dp = new dietplan();

                dp.setDay(rs.getString("day"));
                dp.setMeal(rs.getString("meal"));
                dp.setFoodItems(rs.getString("foodItems"));
                dietPlans.add(dp);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found!");
        }

        return dietPlans;
    }
}
