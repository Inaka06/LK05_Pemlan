public class MainSimulation {
    public static void main(String[] args) {
        PatientProfileV1 andi = new PatientProfileV1("P001", "Andi", "12345");
        IntegrationGateway<PatientProfileV1> gateway1 = new IntegrationGateway<>(andi);
        showResult(gateway1.fetchData("P011", 1), 1); //test dengan patientId acak
        showResult(gateway1.fetchData("P001", 1), 1); //test dengan accessLevel rendah
        showResult(gateway1.fetchData("P001", 3), 3); //test sempurna

        PatientProfileV2 susan = new PatientProfileV2("P002", "Susan", "786324", "Amoxicilin", "089521999789");
        IntegrationGateway<PatientProfileV2> gateway2 = new IntegrationGateway<>(susan);
        showResult(gateway2.fetchData("P001", 1), 1); //test dengan patientId dari objek lain
        showResult(gateway2.fetchData("P002", 2), 2); //test dengan accessLevel rendah
        showResult(gateway2.fetchData("P002", 4), 4); //test sempurna
        
    }

    static void showResult(SecureResponse<?> res, int level) {
        System.out.println("");
        if (res.getData() != null) System.out.println("Tampilan hasil untuk dokter dengan level akses: " + level);
        System.out.println("Data: " + res.getData());
        if (res.getWarningMessage() != null) System.out.println("Warn: " + res.getWarningMessage());
    }
}