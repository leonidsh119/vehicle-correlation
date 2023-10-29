package vehiclecorrelation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbandonedVehicleEvent {
    private List<AbandonedVehicle> vehicles = new ArrayList<>();

    private LocalDateTime reportDate;

    public List<AbandonedVehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<AbandonedVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
}
