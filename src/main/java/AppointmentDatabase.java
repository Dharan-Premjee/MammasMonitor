import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/appointment"; // Your DB URL
    private static final String USER = "root"; 
    private static final String PASSWORD = "honey@053";

    // Method to get all appointments from the database
    public List<Appointment> getAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();

        // Registering the JDBC driver (needed for some older versions)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Register MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        // Connecting to the database
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT doctor_name, patient_name, appointment_date, appointment_time, status FROM appointmentdb"; 
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                // Iterating over the results
                while (rs.next()) {
                    Appointment appointment = new Appointment(); // Creating Appointment object for each row
                    appointment.setDoctorName(rs.getString("doctor_name")); // Setting values from DB
                    appointment.setPatientName(rs.getString("patient_name"));
                    appointment.setAppointmentDate(rs.getString("appointment_date"));
                    appointment.setAppointmentTime(rs.getString("appointment_time"));
                    appointment.setStatus(rs.getString("status"));
                    appointments.add(appointment); // Adding to the list
                }
            }
        }
        return appointments; // Return the list of appointments
    }
}
