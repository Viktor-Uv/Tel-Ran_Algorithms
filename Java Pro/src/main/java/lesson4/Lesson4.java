package lesson4;

import lesson1.japan.JapaneseEmployee;

public class Lesson4 {
    public static void main(String[] args) {
        // primitive types - int, boolean, double, float, byte, char ...
        // name starts with small letter
        // примитивные типы встроены, мы не можем создать свои собственные

        // ссылочные типы это все остальные
        // в том числе и те, которые мы создаем сами

        int a = 5;

        JapaneseEmployee je = new JapaneseEmployee("Akira", 10_000, 3);

        int b = a;

        b = 10;

        System.out.println(a + " " + b);

        Employee e1 = new Employee("Max Kotkov", 25);
        Employee e2 = new Employee("Lena Semenova", 26);
        Employee e3 = e2;
        e3.setName("Valeria Petrova");
        System.out.println("lena: " + e2 + " valeria: " + e3);

        System.out.println("Max before: " + e1);
        something(e1);
        System.out.println("Max after: " + e1);

        System.out.println("Valeria before: " + e2);
        somethingNew(e2);
        System.out.println("Valeria after: " + e2);


    } // main end

    public static void something(Employee e) {
        e.setAge(36);
    }

    public static void somethingNew(Employee e) {
        e = new Employee("Sergey Smirnov", 21);
        System.out.println(e);
        // мы не можем одной по ссылке
        // изменить на что ссылается другая ссылка
    }

}
