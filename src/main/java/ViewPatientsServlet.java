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

@WebServlet("/viewPatients")  // URL pattern for this servlet
public class ViewPatientsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Method to handle the GET request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json"); // Setting the content type as JSON
        response.setCharacterEncoding("UTF-8"); // Ensuring proper character encoding
        PrintWriter out = response.getWriter(); // Output stream to send the response

        // Creating an instance of the database class
        ViewPatientsDatabase db = new ViewPatientsDatabase();

        try {
            // Fetching the list of patients from the database
            List<ViewPatients> patients = db.getPatients();

            // Creating a JSON array to send the response
            JSONArray jsonArray = new JSONArray();

            // Converting the patients list into JSON objects
            for (ViewPatients patient : patients) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("patientId", patient.getPatientId());
                jsonObj.put("name", patient.getName());
                jsonObj.put("age", patient.getAge());
                jsonObj.put("pregnancyMonth", patient.getPregnancyMonth());
                jsonArray.put(jsonObj); // Adding JSON object to the array
            }

            out.print(jsonArray.toString()); // Sending the response back as JSON

        } catch (SQLException e) {
            e.printStackTrace(); // Printing stack trace for debugging
            out.print("{\"error\": \"Database error!\"}"); // Sending error message in JSON format
        } finally {
            out.close(); // Closing the output stream
        }
    }
}
