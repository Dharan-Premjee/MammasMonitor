
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedCheckDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/medcheck?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "honey@053";

    // Method to fetch all medicine intake records
    public List<MedCheck> getMedCheckRecords() throws SQLException {
        List<MedCheck> medCheckRecords = new ArrayList<>();
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found");
        }

        // Connect to the database
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT * FROM medcheckdb";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            String patientName = rs.getString("patient_name");
            String medicineName = rs.getString("medicine_name");
            String intakeDate = rs.getString("intake_date");
            String intakeStatus = rs.getString("intake_status");

            MedCheck medCheck = new MedCheck(patientName, medicineName, intakeDate, intakeStatus);
            medCheckRecords.add(medCheck);
        }

        rs.close();
        stmt.close();
        conn.close();

        return medCheckRecords;
    }
}
