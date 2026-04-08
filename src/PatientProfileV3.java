public class PatientProfileV3 extends PatientProfile implements Confidential<PatientProfileV3>{
    private String allergyHistory;
    private String emergencyNumber;
    private int securityLevel = 7;
    private final int version = 3;
    private String makanan;
    private String netWorth;

    public PatientProfileV3(String patientId, String name, String ssn, String allergyHistory, String emergencyNumber, String netWorth, String makanan){
        super(patientId, name, ssn);
        this.allergyHistory = allergyHistory;
        this.emergencyNumber = emergencyNumber;
        this.makanan = makanan;
        this.netWorth = netWorth;
    }

    public PatientProfileV3(PatientProfileV3 source){
        super(source);
        this.allergyHistory = source.allergyHistory;
        this.emergencyNumber = source.emergencyNumber;
        this.securityLevel = source.securityLevel;
        this.makanan = source.makanan;
        this.netWorth = source.netWorth;
    }

    @Override
    public int getSecurityLevel() { return this.securityLevel; }

    @Override
    public void maskSensitiveData() {
        if (this.ssn != null) this.ssn = "[REDACTED]";
        if (this.allergyHistory != null) this.allergyHistory = "[REDACTED]";
        if (this.emergencyNumber != null) this.emergencyNumber = "[REDACTED]";
        if (this.netWorth != null) this.netWorth = "[REDACTED]";
        if (this.makanan != null) this.makanan = "[REDACTED]";
    }

    @Override
    public PatientProfileV3 copy() { return new PatientProfileV3(this); }

    @Override
    public String toString() {
        return "[V" + version + "]" + super.toString()
                + " | Allergy: " + allergyHistory
                + " | Emergency Number: " + emergencyNumber
                + " | Net Worth: " + netWorth
                + " | Preferensi Makanan: " + makanan;
    }
}