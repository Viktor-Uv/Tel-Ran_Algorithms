package lesson28.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericTester {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Misha"); // - only Strings
//        names.add(4); // Exception

        List something = new ArrayList();
        something.add("Misha");
        something.add(4);

        String name = (String) something.get(0);
//        String anotherName = (String) something.get(1); // Exception

        debug(name);
        debug(4);
        log(name);
        log(4);

        String [] countries = {"Cuba", "Venezuela", "Salvador", "Albania"};
        System.out.println(firstElement(countries));

        System.out.println(Arrays.toString(countries));
        swap(countries, 1, 2);
        System.out.println(Arrays.toString(countries));

        Pair<String, Integer> masha = new PairImpl<>("Masha", 24);

        System.out.println(firstAndLast(countries));

        List<String> countriesList = fromArrayToList(countries);
        System.out.println(countriesList);

        System.out.println(
                fromArrayToList(
                        countries,
                        s -> s.contains("l"), // predicate
                        s -> s.toUpperCase() // mapper
                )
        );

    } // Main

    public static <T, R> List<R> fromArrayToList(
            T[] a,
            Predicate<T> predicate, // checks T
            Function<T, R> mapper
    ) {
        return Arrays.stream(a)
                .filter(predicate)
                .map(mapper)
                .collect(Collectors.toList());
    }

    // напишите шаблонную функцию, которая преобразует массив чего угодно в
    // список
    public static <T> List<T> fromArrayToList(T[] a) {
        return Arrays.stream(a)
                .collect(Collectors.toList());
    }

    // напишите функцию, которая принимает на вход массив чего угодно и возвращает
    // пару из первого и последнего элемента
    public static <T> Pair<T, T> firstAndLast(T[] a) {
        return new PairImpl<>(
                a[0],
                a[a.length - 1]
        );
    }

    // напишите функцию swap которая принимает на вход массив чего угодно
    // и два номера
    // внутри массива обменивает элементы по этим номерам
    public static <R> void swap(R[] s, int i, int j) {
        R temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    // напишите шаблонную функцию которая принимает на вход массив чего угодно
    // и возвращает первый элемент этого массива
    public static <T> T firstElement(T[] t) {
        return t[0];
    }

    // Generic == Шаблон
    // T - типовой параметр
    public static <T> void log(T t) { // t - значение, T - тип
        System.out.println(
                "It's a " + t.getClass().getSimpleName() + ", value is: " + t
        );
    }

    public static void debug(int i ) {
        System.out.println("It's a int, value is: " + i);
    }
    public static void debug(String s) {
        System.out.println("It's a String, value is: " + s);
    }

}
