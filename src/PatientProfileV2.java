public class PatientProfileV2 implements MedicalRecord, Versioned, Confidential<PatientProfileV2> {
    private String patientId;
    private String name;
    private String ssn;
    private String allergyHistory;
    private int securityLevel = 3;
    private String emergencyNumber;

    public PatientProfileV2(String patientId, String name, String ssn, String allergyHistory, String emergencyNumber) {
        this.patientId = patientId;
        this.name = name;
        this.ssn = ssn;
        this.allergyHistory = allergyHistory;
        this.emergencyNumber = emergencyNumber;
    }

    public PatientProfileV2(PatientProfileV2 source) {
        this.patientId = source.patientId;
        this.name = source.name;
        this.ssn = source.ssn;
        this.allergyHistory = source.allergyHistory;
        this.securityLevel = source.securityLevel;
        this.emergencyNumber = source.emergencyNumber;
    }

    @Override
    public String getPatientId() { return patientId; }
    @Override
    public int getVersion() { return 2; }
    @Override
    public int getSecurityLevel() { return securityLevel; }

    @Override
    public void maskSensitiveData() {
        if (this.ssn != null) this.ssn = "******";
        if (this.allergyHistory != null) this.allergyHistory = "[REDACTED]";
        if (this.emergencyNumber!= null) this.emergencyNumber = "*******";
    }

    @Override
    public PatientProfileV2 copy() {
        return new PatientProfileV2(this);
    }

    @Override
    public String toString() {
        return "[V2] Patient: " + name + " | SSN: " + ssn + " | Allergies: " + allergyHistory + " | Emergency Number : " + emergencyNumber;
    }
}