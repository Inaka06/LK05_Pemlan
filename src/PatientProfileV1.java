public class PatientProfileV1 implements MedicalRecord, Versioned, Confidential<PatientProfileV1> {
    private String patientId;
    private String name;
    private String ssn;
    private int securityLevel = 2;

    public PatientProfileV1(String patientId, String name, String ssn) {
        this.patientId = patientId;
        this.name = name;
        this.ssn = ssn;
    }

    // Copy Constructor
    public PatientProfileV1(PatientProfileV1 source) {
        this.patientId = source.patientId;
        this.name = source.name;
        this.ssn = source.ssn;
        this.securityLevel = source.securityLevel;
    }

    @Override
    public String getPatientId() { return patientId; }
    @Override
    public int getVersion() { return 1; }
    @Override
    public int getSecurityLevel() { return securityLevel; }

    @Override
    public void maskSensitiveData() {
        if (this.ssn != null) this.ssn = "******";
    }

    @Override
    public PatientProfileV1 copy() {
        return new PatientProfileV1(this);
    }

    @Override
    public String toString() {
        return "[V1] Patient: " + name + " | SSN: " + ssn;
    }
}