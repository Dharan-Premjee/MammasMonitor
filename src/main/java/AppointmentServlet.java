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

@WebServlet("/appointments")  // URL pattern for this servlet
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Method to handle the GET request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json"); // Setting the content type as JSON
        response.setCharacterEncoding("UTF-8"); // Ensuring proper character encoding
        PrintWriter out = response.getWriter(); // Output stream to send the response

        // Log to check if the servlet is being called
        System.out.println("Servlet for Appointments called!");

        // Creating an instance of the database class
        AppointmentDatabase db = new AppointmentDatabase();

        try {
            // Fetching the list of appointments from the database
            List<Appointment> appointments = db.getAppointments();

            // Print fetched appointments to debug
            System.out.println("Fetched Appointments: " + appointments);

            // Creating a JSON array to send the response
            JSONArray jsonArray = new JSONArray();

            // Converting the appointments list into JSON objects
            for (Appointment appointment : appointments) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("doctorName", appointment.getDoctorName());
                jsonObj.put("patientName", appointment.getPatientName());
                jsonObj.put("appointmentDate", appointment.getAppointmentDate());
                jsonObj.put("appointmentTime", appointment.getAppointmentTime());
                jsonObj.put("status", appointment.getStatus());
                jsonArray.put(jsonObj); // Adding JSON object to the array
            }

            // Sending the response back as JSON
            out.print(jsonArray.toString());

        } catch (SQLException e) {
            e.printStackTrace(); // Printing stack trace for debugging
            out.print("{\"error\": \"Database error!\"}"); // Sending error message in JSON format
        } finally {
            out.close(); // Closing the output stream
        }
    }
}
