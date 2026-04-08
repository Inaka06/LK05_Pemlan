public class SecureResponse<T extends MedicalRecord & Confidential<T>> {
    private boolean success;
    private T data;
    private String warningMessage;

    public SecureResponse(boolean success, T data, String warningMessage) {
        this.success = success;
        this.data = data;
        this.warningMessage = warningMessage;
    }

    public T getData() { return data; }
    public String getWarningMessage() { return warningMessage; }
}