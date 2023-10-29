package vehiclecorrelation.model;

import java.io.Serializable;
import java.util.Objects;

public class VehicleColor implements Serializable {
    int r;
    int g;
    int b;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleColor that = (VehicleColor) o;
        return r == that.r && g == that.g && b == that.b; // TODO: Tolerance
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }
}
