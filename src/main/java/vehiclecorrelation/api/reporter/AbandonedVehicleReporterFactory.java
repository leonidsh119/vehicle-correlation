package vehiclecorrelation.api.reporter;

public class AbandonedVehicleReporterFactory {
    public AbandonedVehicleReporter createReporter() {
        return new SQSAbandonedVehicleReporter();
    }
}
