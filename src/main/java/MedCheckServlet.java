import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/medcheck")
public class MedCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        MedCheckDatabase db = new MedCheckDatabase();
        
        try {
            List<MedCheck> medCheckRecords = db.getMedCheckRecords();

            JSONArray jsonArray = new JSONArray();
            for (MedCheck medCheck : medCheckRecords) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("patientName", medCheck.getPatientName());
                jsonObj.put("medicineName", medCheck.getMedicineName());
                jsonObj.put("intakeDate", medCheck.getIntakeDate());
                jsonObj.put("intakeStatus", medCheck.getIntakeStatus());
                jsonArray.put(jsonObj);
            }

            out.print(jsonArray.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("{\"error\": \"Database error!\"}");
        } finally {
            out.close();
        }
    }
}
