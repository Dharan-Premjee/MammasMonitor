import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class Patientloginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        patientlogindatabase dao = new patientlogindatabase();
        user patient = null;

        try {
            patient = dao.validateUser(email, password);

            if (patient != null) {
                response.sendRedirect("html/patientdashboard.html");
            } else {
                out.print("<script>alert('Invalid email or password!'); window.location='html/patientlogin.html';</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("<script>alert('Database error! Please try again later.'); window.location='html/patientlogin.html';</script>");
        } finally {
            out.close();
        }
    }
}
