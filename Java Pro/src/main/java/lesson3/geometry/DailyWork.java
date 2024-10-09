package lesson3.geometry;

import lesson2.crossword.C;

public class DailyWork {
    public static void main(String[] args) {
        Figure [] work = {
                new Rectangle(2, 3),
                new Rectangle(4, 5),
                new Circle(2),
                new Circle(5)
        };

        // нельзя создать экземпляр абстрактного класса
        // Figure f = new Figure();

        double sumPerimeter = 0;
        double sumArea = 0;
        for (int i = 0; i < work.length; i++) {
            sumPerimeter += work[i].perimeter();
            sumArea += work[i].area();

            // можно вызвать функцию из абстрактного класса
            // work[i].hello();

            // имеем доступ к полю абстрактного класса
            // work[i].name
        }

        System.out.println("sumPerimiter = " + sumPerimeter +
                "; sumArea = " + sumArea);
    }
}
