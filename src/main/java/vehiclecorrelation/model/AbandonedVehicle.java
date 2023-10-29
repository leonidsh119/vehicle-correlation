package vehiclecorrelation.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class AbandonedVehicle extends SuspectedVehicle {
    private String camId;

    public AbandonedVehicle() {
        super();
    }

    public AbandonedVehicle(SuspectedVehicle other, String camId) {
        super(other);
        this.camId = camId;
    }

    public String getCamId() {
        return camId;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbandonedVehicle that = (AbandonedVehicle) o;
        return Objects.equals(camId, that.camId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), camId);
    }
}
