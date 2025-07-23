public class MedCheck {
    private String patientName;
    private String medicineName;
    private String intakeDate;
    private String intakeStatus;

    public MedCheck(String patientName, String medicineName, String intakeDate, String intakeStatus) {
        this.patientName = patientName;
        this.medicineName = medicineName;
        this.intakeDate = intakeDate;
        this.intakeStatus = intakeStatus;
    }

    public String getPatientName() { return patientName; }
    public String getMedicineName() { return medicineName; }
    public String getIntakeDate() { return intakeDate; }
    public String getIntakeStatus() { return intakeStatus; }
}
