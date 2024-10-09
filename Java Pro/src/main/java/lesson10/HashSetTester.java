package lesson10;

/*
    если планируем использовать для наших собственных классов
    хэшированные контейнеры (HashSet, HashMap etc) то нужно:
    0. заимплементить в классе equals() и hashCode()
    у Object.hashCode() есть контракт
    1. На протяжении жизни объекта hashCode() должен выдавать одно и то же целое
    2. Если два объекта экавивалентны, то их хэш-коды должны быть одинаковы
        обратное - неверно

 */

import java.util.HashSet;
import java.util.Set;

public class HashSetTester {
    public static void main(String[] args) {
        Set<Point> points = new HashSet<>();
        Point p1 = new Point(10, 20);
        Point p2 = new Point(10, 20);
        points.add(p1);
        points.add(p2);
        // так как не переопределён equals(), то объекты сравниваются по адресу в памяти
        // так как не переопределён hashCode(), то он возвращает адрес объекта в памяти
        System.out.println(points.size()); // 1 or 2?

        Set<Point> newPoints = new HashSet<>();
        Point newPoint = new Point(100, 200);
        newPoints.add(newPoint);
        newPoint.setY(10_000);
        newPoints.add(newPoint);
        // hashCode разный и equals тоже разный
        System.out.println(newPoints.size()); // 1 or 2?

    }
}
