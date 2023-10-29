package vehiclecorrelation.api.data;

import jdk.jshell.spi.ExecutionControl;
import vehiclecorrelation.model.SuspectedVehicle;

import java.util.List;

public class DynamoSuspectedVehiclesDAO implements SuspectedVehiclesDAO {
    @Override
    public List<SuspectedVehicle> getSuspectedVehicles(String cameraId) {
        throw new UnsupportedOperationException("DynamoSuspectedVehiclesDAO::getSuspectedVehicles not implemented yet.");
    }

    @Override
    public void save(String cameraId, List<SuspectedVehicle> suspected) {
        throw new UnsupportedOperationException("DynamoSuspectedVehiclesDAO::save not implemented yet.");
    }
}
