package exercise;

// BEGIN
interface Home extends Comparable<Home> {
    double getArea();

    @Override
    default int compareTo(Home o) {
        return Double.compare(this.getArea(), o.getArea());
    }
}
// END
