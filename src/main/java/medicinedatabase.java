import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class medicinedatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/medicineschedule";
    private static final String USER = "root";
    private static final String PASSWORD = "honey@053";

    public List<medicineschedule> getMedicineSchedule() throws SQLException {
        List<medicineschedule> medicines = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT name, dosage, time, instructions FROM medicinescheduledb";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    medicineschedule med = new medicineschedule();
                    med.setName(rs.getString("name"));
                    med.setDosage(rs.getString("dosage"));
                    med.setTime(rs.getString("time"));
                    med.setInstructions(rs.getString("instructions"));
                    medicines.add(med);
                }
            }
        }
        return medicines;
    }
}
