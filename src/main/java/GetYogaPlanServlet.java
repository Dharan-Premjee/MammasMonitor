import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/getYogaPlan")
public class GetYogaPlanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // Database Connection Variables
        String jdbcURL = "jdbc:mysql://localhost:3306/getyoga";
        String dbUser = "root";
        String dbPassword = "honey@053";

        List<getyoga> yogaPlans = new ArrayList<>();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT trimester, pose_name, benefits FROM getyogadb";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Fetch Data from Database
            while (rs.next()) {
                getyoga yoga = new getyoga();
                yoga.setTrimester(rs.getString("trimester"));
                yoga.setPoseName(rs.getString("pose_name"));
                yoga.setBenefits(rs.getString("benefits"));
                yogaPlans.add(yoga);
            }

            // Convert Data to JSON
            JSONArray jsonArray = new JSONArray();
            for (getyoga yoga : yogaPlans) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("trimester", yoga.getTrimester());
                jsonObj.put("pose_name", yoga.getPoseName());
                jsonObj.put("benefits", yoga.getBenefits());
                jsonArray.put(jsonObj);
            }

            // Send JSON Response
            out.print(jsonArray.toString());

            // Close Resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("{\"error\": \"Database error!\"}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.print("{\"error\": \"JDBC Driver not found!\"}");
        } finally {
            out.close();
        }
    }
}
