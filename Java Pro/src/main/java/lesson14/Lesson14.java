package lesson14; // Date 09.08.2023

import java.util.*;

public class Lesson14 {
    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>(Arrays.asList(
                new Fruit("Apple", 52),
                new Fruit("Bananas", 89),
                new Fruit("Grapes", 67),
                new Fruit("Plums", 67),
                new Fruit("Lemon", 29)
        ));
        System.out.println(fruits);


        // отсортируйте по количеству калорий по убыванию
//        Comparator<Fruit> fruitComparator = new Comparator<Fruit>() {
//            @Override
//            public int compare(Fruit f1, Fruit f2) {
//                return Integer.compare(f1.getCalories(), f2.getCalories());
//            }
//        };
        fruits.sort(
                Comparator.comparingInt(Fruit::getCalories).reversed()
        );
        System.out.println(fruits);

        // сортировка вначале по убыванию калорий и потом по убыванию имен
        fruits.sort(
                Comparator.comparingInt(Fruit::getCalories).reversed()
                        .thenComparing(Comparator.comparing(Fruit::getName).reversed())
        );
        System.out.println(fruits);

        // элемент с максимальной калорийностью
        Fruit max = Collections.max(fruits, Comparator.comparingInt(Fruit::getCalories));
        System.out.println(max);

        Fruit lemon = new Fruit("Lemon", 29);
        fruits.sort(Comparator.comparing(Fruit::getName));
        System.out.println(fruits);
        System.out.println(
                Collections.binarySearch(
                        fruits,
                        lemon,
                        Comparator.comparing(Fruit::getName)
                )
        );

        // фрукты по возрастанию калорийности
        // При создании TreeSet можно задать компаратор
        TreeSet<Fruit> fruitSet = new TreeSet<>(Comparator.comparing(Fruit::getCalories));
        fruitSet.addAll(fruits);
        System.out.println(fruitSet);

        // find by range
        System.out.println(
                fruitSet.subSet(
                        new Fruit("Kiwi", 30),
                        new Fruit("Pomelo", 70)
                )
        );
        // fruitSet.tailSet() // набор тех кто больше
        // fruitSet.headSet() // набор тех кто меньше

    }
}
