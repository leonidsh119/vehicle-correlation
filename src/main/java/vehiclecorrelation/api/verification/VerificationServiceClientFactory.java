package vehiclecorrelation.api.verification;

public class VerificationServiceClientFactory {
    public VerificationServiceClient createClient() {
        return new RestVerificationServiceClient();
    }
}
