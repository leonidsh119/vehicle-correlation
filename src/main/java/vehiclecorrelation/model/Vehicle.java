package vehiclecorrelation.model;

import java.io.Serializable;
import java.util.Objects;

public class Vehicle  implements Serializable {
    protected String type;
    protected VehicleColor color;
    protected VehiclePosition positionOnSourceImage;

    public Vehicle() {
    }

    public Vehicle(Vehicle other) {
        this.type = other.type;
        this.color = other.color;
        this.positionOnSourceImage = other.positionOnSourceImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VehicleColor getColor() {
        return color;
    }

    public void setColor(VehicleColor color) {
        this.color = color;
    }

    public VehiclePosition getPositionOnSourceImage() {
        return positionOnSourceImage;
    }

    public void setPositionOnSourceImage(VehiclePosition positionOnSourceImage) {
        this.positionOnSourceImage = positionOnSourceImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(type, vehicle.type) && Objects.equals(color, vehicle.color) && Objects.equals(positionOnSourceImage, vehicle.positionOnSourceImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color, positionOnSourceImage);
    }
}
