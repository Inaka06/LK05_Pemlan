public class PatientProfile implements MedicalRecord, Versioned {
    private String patientId;
    private String name;
    String ssn;
    private final int version = 0;

    public PatientProfile(String patientId, String name, String ssn) {
        this.patientId = patientId;
        this.name = name;
        this.ssn = ssn;
    }

    public PatientProfile(PatientProfile source) {
        this.patientId = source.patientId;
        this.name = source.name;
        this.ssn = source.ssn;
    }

    @Override public String getPatientId() { return patientId; }
    @Override public int getVersion() { return version; }

    @Override
    public String toString() {
        return "Patient: " + name + " | SSN: " + ssn;
    }
}