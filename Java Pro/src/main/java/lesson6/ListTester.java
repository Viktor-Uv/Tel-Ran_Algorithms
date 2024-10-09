package lesson6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTester {
    public static void main(String[] args) {
        // List - динамически расширяемый список элементов
        //      ArrayList - основан на масиве
        //      LinkedList - каждый из элементов хранит ссылку на следующий и предыдущих
        // Set - набор уникальных элементов
        // Map - хранит пары из уникального ключа и значения

        // инициализация
        List<String> capitals = new ArrayList<>(
                Arrays.asList("Vienna", "Prague")
        );
        System.out.println(capitals + " size: " + capitals.size());
        capitals.add("Berlin"); // добавление элемента в конец
        System.out.println(capitals + " size: " + capitals.size());
        // добавлние коллекции в конец
        capitals.addAll(Arrays.asList("London", "Paris"));
        System.out.println(capitals + " size: " + capitals.size());
        System.out.println(capitals.get(3)); // обращение по индексу
        // изменение по индексу
        capitals.set(0, "Bratislava");
        System.out.println(capitals);
        // удаление элемента
        capitals.remove(4); // удалите Paris
        capitals.remove("Paris");
        System.out.println(capitals);

        System.out.println(concat(capitals, Arrays.asList("c1", "c2")));

        System.out.println(capitals);

        // можно добавить в список коллекцию
        // capitals.addAll(Collection)
        // можно удалить из списка все элементы, входящие в другую коллекцию
        // capitals.removeAll(Collection)

        // Все контейнеры/коллеции явы могут содержать ТОЛЬКО ссылочные типы

        System.out.println(
                compare(Arrays.asList(1,2,3,4), Arrays.asList(1,2,3,4)) // true
        );
        System.out.println(
                compare(Arrays.asList(1,2,3), Arrays.asList(1,2,4)) // false
        );

        // ArrayList - основан на массиве
        // все операции доступа по индексу O(1)
        // операции добавления в начало O(N)

        System.out.println(
                filterOnlyEven(Arrays.asList(1,2,6,8,15,3)) // [2, 6, 8]
        );

        // https://docs.oracle.com/javase/8/docs/api/java/util/List.html

    } // окончание main

    // напишите функцию которая принимает список целых и возвращает из
    // него только четные
    // {1,2,6,8,15,3} -> {2,6,8}
    public static List<Integer> filterOnlyEven(List<Integer> src) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < src.size(); i++) {
            if (src.get(i) % 2 == 0) {
                out.add(src.get(i));
            }
        }
        return out;
    }

    // напишите функцию которая принимает на вход два списка целых
    // и возвращает true если все элементы попарно одинаковы
    public static boolean compare(List<Integer> i1, List<Integer> i2) {
        for (int i = 0; i < i1.size(); i++) {
            if (!i1.get(i).equals(i2.get(i))) {
                return false;
            }
        }
        return true;
    }

    // напишите функцию которая конкатенирует два списка
    public static List<String> concat(List<String> s1, List<String> s2) {
        List<String> s3 = new ArrayList<>(s1);
        s3.addAll(s2);
        return s3;
    }

}