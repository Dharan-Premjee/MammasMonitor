import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class yogadatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/getyoga";
    private static final String USER = "root";
    private static final String PASSWORD = "honey@053";

    public List<getyoga> getYogaPlan() throws SQLException {
        List<getyoga> yogaPlans = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT trimester, pose_name, benefits FROM getyogadb";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    getyoga yoga = new getyoga();
                    yoga.setTrimester(rs.getString("trimester"));
                    yoga.setPoseName(rs.getString("pose_name"));
                    yoga.setBenefits(rs.getString("benefits"));
                    yogaPlans.add(yoga);
                }
            }
        }
        return yogaPlans;
    }
}
