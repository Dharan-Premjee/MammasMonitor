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

@WebServlet("/getMedicineSchedule")
public class GetMedicineScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // Database Connection Variables
        String jdbcURL = "jdbc:mysql://localhost:3306/medicineschedule";
        String dbUser = "root";
        String dbPassword = "honey@053";

        List<medicineschedule> medicines = new ArrayList<>();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT name, dosage, time, instructions FROM medicinescheduledb";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Fetch Data from Database
            while (rs.next()) {
                medicineschedule med = new medicineschedule();
                med.setName(rs.getString("name"));
                med.setDosage(rs.getString("dosage"));
                med.setTime(rs.getString("time"));
                med.setInstructions(rs.getString("instructions"));
                medicines.add(med);
            }

            // Convert Data to JSON
            JSONArray jsonArray = new JSONArray();
            for (medicineschedule med : medicines) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("name", med.getName());
                jsonObj.put("dosage", med.getDosage());
                jsonObj.put("time", med.getTime());
                jsonObj.put("instructions", med.getInstructions());
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
