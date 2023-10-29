package vehiclecorrelation.model;

import java.time.LocalDateTime;

public class SuspectedVehicle extends Vehicle {
    private LocalDateTime detectedSince;
    private String lastClipImageLocation;

    public SuspectedVehicle() {
        super();
    }

    public SuspectedVehicle(Vehicle other) {
        super(other);
    }

    public SuspectedVehicle(Vehicle other, LocalDateTime detectedSince, String lastClipImageLocation) {
        super(other);
        this.detectedSince = detectedSince;
        this.lastClipImageLocation = lastClipImageLocation;
    }

    public SuspectedVehicle(SuspectedVehicle other) {
        super(other);
        this.detectedSince = other.detectedSince;
        this.lastClipImageLocation = other.lastClipImageLocation;
    }

    public LocalDateTime getDetectedSince() {
        return detectedSince;
    }

    public void setDetectedSince(LocalDateTime detectedSince) {
        this.detectedSince = detectedSince;
    }

    public String getLastClipImageLocation() {
        return lastClipImageLocation;
    }

    public void setLastClipImageLocation(String lastClipImageLocation) {
        this.lastClipImageLocation = lastClipImageLocation;
    }
}
