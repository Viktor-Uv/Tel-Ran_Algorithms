package lesson1.geometry;

public class Rectangle {
    final private Point sw; // final - no changes allowed; attempts will be marked as error
    final private Point ne;

    public Rectangle(Point sw, Point ne) {
        this.sw = sw;
        this.ne = ne;
    }

    public Point getSw() {
        return sw;
    }

    public Point getNe() {
        return ne;
    }

    public double area() {
        double x = ne.getX() - sw.getX();
        double y = ne.getY() - sw.getY();
        return x * y;
    }

}
