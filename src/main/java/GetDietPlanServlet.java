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

@WebServlet("/dietplan")
public class GetDietPlanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Initialize the database object
            dietplandatabase dao = new dietplandatabase();

            // Fetch the diet plans from the database
            List<dietplan> dietPlans = dao.getDietPlan();

            if (dietPlans != null && !dietPlans.isEmpty()) {
                // Create a JSON array to store the diet plans
                JSONArray jsonArray = new JSONArray();

                for (dietplan dp : dietPlans) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("day", dp.getDay());
                    jsonObj.put("meal", dp.getMeal());
                    jsonObj.put("foodItems", dp.getFoodItems());
                    jsonArray.put(jsonObj);
                }

                // Output the JSON response
                out.print(jsonArray.toString());
            } else {
                // Return empty JSON array if no data
                out.print("[]");
            }
        } catch (SQLException e) {
            // Handle database errors and send a JSON error response
            e.printStackTrace();
            out.print("{\"error\": \"Database error!\"}");
        } finally {
            out.close(); // Ensure the writer is closed to avoid resource leakage
        }
    }
}
