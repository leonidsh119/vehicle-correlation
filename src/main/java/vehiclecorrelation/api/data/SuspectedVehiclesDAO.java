package vehiclecorrelation.api.data;

import vehiclecorrelation.model.SuspectedVehicle;

import java.util.List;

public interface SuspectedVehiclesDAO {
    List<SuspectedVehicle> getSuspectedVehicles(String cameraId);

    void save(String cameraId, List<SuspectedVehicle> suspected);
}
