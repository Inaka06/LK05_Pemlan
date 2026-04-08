public class PatientProfileV1 extends PatientProfile implements Confidential<PatientProfileV1> {
    private int securityLevel = 2;
    private final int version = 1;

    public PatientProfileV1(String patientId, String name, String ssn) {
        super(patientId, name, ssn);
    }

    public PatientProfileV1(PatientProfileV1 source) {
        super(source);
        this.securityLevel = source.securityLevel;
    }

    @Override
    public void maskSensitiveData() {
        if (this.ssn != null) this.ssn = "[REDACTED]";
    }

    @Override
    public int getSecurityLevel() { return this.securityLevel; }

    @Override
    public PatientProfileV1 copy() { return new PatientProfileV1(this); }

    @Override
    public String toString() { return "[V" + version + "]" + super.toString(); }
}