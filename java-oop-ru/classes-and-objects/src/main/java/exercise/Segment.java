package exercise;

// BEGIN
class Segment {
    private final Point beginPoint;
    private final Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        int x = beginPoint.getX() + (endPoint.getX() - beginPoint.getX()) / 2;
        int y = beginPoint.getY() + (endPoint.getY() - beginPoint.getY()) / 2;
        return new Point(x, y);
    }
}
// END
