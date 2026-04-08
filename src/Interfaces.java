interface MedicalRecord {
    String getPatientId();
}

interface Versioned {
    int getVersion();
}

interface Confidential<T> {
    int getSecurityLevel();
    void maskSensitiveData();
    T copy();
}