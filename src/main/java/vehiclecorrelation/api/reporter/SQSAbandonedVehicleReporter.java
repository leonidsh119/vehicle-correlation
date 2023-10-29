package vehiclecorrelation.api.reporter;

import vehiclecorrelation.model.AbandonedVehicleEvent;

public class SQSAbandonedVehicleReporter implements AbandonedVehicleReporter {
    @Override
    public void publish(AbandonedVehicleEvent event) {
        throw new UnsupportedOperationException("SQSAbandonedVehicleReporter::publish not implemented yet.");
    }
}
