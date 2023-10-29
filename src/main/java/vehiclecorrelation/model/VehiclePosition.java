package vehiclecorrelation.model;

import java.io.Serializable;
import java.util.Objects;

public class VehiclePosition implements Serializable {
    private VehiclePositionPoint topLeft;
    private VehiclePositionPoint bottomRight;

    public VehiclePositionPoint getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(VehiclePositionPoint topLeft) {
        this.topLeft = topLeft;
    }

    public VehiclePositionPoint getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(VehiclePositionPoint bottomRight) {
        this.bottomRight = bottomRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiclePosition that = (VehiclePosition) o;
        return Objects.equals(topLeft, that.topLeft) && Objects.equals(bottomRight, that.bottomRight); // TODO: Tolerance
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight);
    }
}
