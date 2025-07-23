import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/doctorlogin") // URL pattern for doctor login
public class DoctorLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 🔹 Get login credentials
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        DatabaseConnection dao = new DatabaseConnection();
        user doctor = null; 

        try {
            // Validate doctor credentials from database
            doctor = dao.validateUser(email, password);

            if (doctor != null && email.equals(doctor.getEmail()) && password.equals(doctor.getPassword())) {
                response.sendRedirect("html/doctordashboard.html");  // ✅ Redirect to doctor dashboard
            } else {
                out.print("<script>alert('Invalid email or password!'); window.location='html/doctorlogin.html';</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("<script>alert('Database error! Please try again later.'); window.location='html/doctorlogin.html';</script>");
        } finally {
            out.close();
        }
    }
}
