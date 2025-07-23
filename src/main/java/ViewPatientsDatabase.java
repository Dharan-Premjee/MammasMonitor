import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewPatientsDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/patients"; 
    private static final String USER = "root";
    private static final String PASSWORD = "honey@053";

    // Method to get all patients from the database
    public List<ViewPatients> getPatients() throws SQLException {
        List<ViewPatients> patients = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Register MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        // Connecting to the database
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT patient_id, name, age, pregnancy_month FROM patientsdb"; // Your table name
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                // Iterating over the results
                while (rs.next()) {
                    ViewPatients patient = new ViewPatients(); // Creating ViewPatients object for each row
                    patient.setPatientId(rs.getInt("patient_id")); // Setting values from DB
                    patient.setName(rs.getString("name"));
                    patient.setAge(rs.getInt("age"));
                    patient.setPregnancyMonth(rs.getInt("pregnancy_month"));
                    patients.add(patient); // Adding to the list
                }
            }
        }
        return patients; // Return the list of patients
    }
}
