public class ViewPatients {
    private int patientId;
    private String name;
    private int age;
    private int pregnancyMonth;

    // ✅ Default Constructor
    public ViewPatients() {}

    // ✅ Constructor
    public ViewPatients(int patientId, String name, int age, int pregnancyMonth) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.pregnancyMonth = pregnancyMonth;
    }

    // ✅ Getters & Setters
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getPregnancyMonth() {
        return pregnancyMonth;
    }
    public void setPregnancyMonth(int pregnancyMonth) {
        this.pregnancyMonth = pregnancyMonth;
    }

    // ✅ toString() for Debugging
    @Override
    public String toString() {
        return "ViewPatients { " +
               "patientId=" + patientId +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", pregnancyMonth=" + pregnancyMonth +
               " }";
    }
}
