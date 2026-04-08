public class PatientProfileV2 extends PatientProfile implements Confidential<PatientProfileV2> {
    private String allergyHistory;
    private String emergencyNumber;
    private int securityLevel = 3;
    private final int version = 2;

    public PatientProfileV2(String patientId, String name, String ssn,
                            String allergyHistory, String emergencyNumber) {
        super(patientId, name, ssn);
        this.allergyHistory = allergyHistory;
        this.emergencyNumber = emergencyNumber;
    }

    public PatientProfileV2(PatientProfileV2 source) {
        super(source);
        this.allergyHistory = source.allergyHistory;
        this.emergencyNumber = source.emergencyNumber;
        this.securityLevel = source.securityLevel;
    }

    @Override
    public int getSecurityLevel() { return this.securityLevel; }

    @Override
    public void maskSensitiveData() {
        if (this.ssn != null) this.ssn = "[REDACTED]";
        if (this.allergyHistory != null) this.allergyHistory = "[REDACTED]";
        if (this.emergencyNumber != null) this.emergencyNumber = "[REDACTED]";
    }

    @Override
    public PatientProfileV2 copy() { return new PatientProfileV2(this); }

    @Override
    public String toString() {
        return "[V" + version + "]" + super.toString()
                + " | Allergy: " + allergyHistory
                + " | Emergency: " + emergencyNumber;
    }
}