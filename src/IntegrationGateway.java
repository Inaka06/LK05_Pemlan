public class IntegrationGateway<T extends MedicalRecord & Versioned & Confidential<T>> {
    private T mockingData;

    public IntegrationGateway(T record) {
        this.mockingData = record;
    }

    public SecureResponse<T> fetchData(String patientId, int accessLevel) {
        if (!mockingData.getPatientId().equals(patientId)) {
            return new SecureResponse<>(false, null, "Patient not found");
        }

        T dupeData = mockingData.copy();

        String warning = null;
        if (accessLevel < dupeData.getSecurityLevel()) {
            dupeData.maskSensitiveData();
            warning = "Some data has been redacted due to insufficient access level";
        }

        return new SecureResponse<>(true, dupeData, warning);
    }
}