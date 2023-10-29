package vehiclecorrelation.api.reporter;

import vehiclecorrelation.model.AbandonedVehicleEvent;

public interface AbandonedVehicleReporter {
    void publish(AbandonedVehicleEvent event);
}
