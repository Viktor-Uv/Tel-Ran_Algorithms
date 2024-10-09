package lesson1.geometry;

public class GeometryTester {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(15, 44);
        Rectangle r1 = new Rectangle(p1, p2);
        System.out.println(r1.area());
    }
}
