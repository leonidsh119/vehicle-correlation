package vehiclecorrelation.api.verification;

public interface VerificationServiceClient {
    int getLikehoodScore(String url1, String url2);
}
