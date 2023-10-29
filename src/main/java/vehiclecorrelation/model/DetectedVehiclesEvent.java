package vehiclecorrelation.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetectedVehiclesEvent implements Serializable {
    private String camId;
    private LocalDateTime dateTime;
    private String sourceS3Object;
    private List<Vehicle> vehicles = new ArrayList<>();

    public String getCamId() {
        return camId;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSourceS3Object() {
        return sourceS3Object;
    }

    public void setSourceS3Object(String sourceS3Object) {
        this.sourceS3Object = sourceS3Object;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
