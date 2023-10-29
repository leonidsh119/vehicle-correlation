package vehiclecorrelation.api.data;

public class SuspectedVehiclesDAOFactory {
    public SuspectedVehiclesDAO createDao() {
        return new DynamoSuspectedVehiclesDAO();
    }
}
