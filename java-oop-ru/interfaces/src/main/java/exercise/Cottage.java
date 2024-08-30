package exercise;

import java.util.Locale;

// BEGIN
class Cottage implements Home {
    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                floorCount + " этажный коттедж площадью " + getArea() + " метров");
    }
}
// END
