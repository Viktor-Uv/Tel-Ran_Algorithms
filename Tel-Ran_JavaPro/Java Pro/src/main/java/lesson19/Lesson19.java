package lesson19; // Date 28.08.2023

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 */

public class Lesson19 {
    public static void main(String[] args) {
        // Stream API: Stream, primitive streams. Intermediate and Terminal operations.

        List<Integer> years = Arrays.asList(2000, 2022, 2021, 2027);

        // поток не запустится, пока к нему не присоединена терминальная операция!
        Stream<Integer> yearsStream = years.stream()
                .filter(y -> (y & 2) == 0) // промежуточная операция
                .filter(y -> y > 2010); // промежуточная операция

        yearsStream.forEach( // одна из терминальных операций
//                integer -> System.out.println(integer)
                System.out::println
        );

        String[] names = {"Alexander", "Max", "Xenia", "Maria"};

        Arrays.stream(names)
                // отфильтруйте только те что содержат x или X - filter
                .filter(s -> s.toUpperCase().contains("X"))
                // переведите имена в верхний регистр - map
                .map(s -> s.toUpperCase())
                // распечатайте - forEach
                .forEach(System.out::println);

        // .map() Returns a stream consisting of the results of applying
        //        the given function to the elements of this stream.

        // IntStream.range(1,5) // 1,2,3,4
        // IntStream.rangeClosed(1,5) // 1,2,3,4,5

        DoubleStream.generate(() -> new Random().nextDouble())
                .limit(10)
                .forEach(System.out::println);

        IntStream.rangeClosed(0, 10)
                .skip(3)
                .limit(3)
                .forEach(System.out::println); // 3,4,5

        // names
        // преобразуйте "имя" -> "имя:3"
        // распечатайте
        Arrays.stream(names)
                .map(s -> s + ":" + s.length())
                .forEach(System.out::println);

        System.out.println(
                Arrays.stream(names)
                        .map(s -> s + ":" + s.length())
                        .count() // количество элементов в потоке
        );

        Arrays.stream(names)
                .map(s -> s + ":" + s.length())
                .sorted()
                .forEach(System.out::println);

        Stream.of(1, 10, 20, 20, 0, 10)
                .distinct() // пропустит только не повторяющиеся элементы 1 10 20 0
                .forEach(System.out::println);

        // peek - позволяет заглянуть в поток
        Arrays.stream(names)
                .peek(s -> System.out.println(s.length()))
                .forEach(System.out::println);


        // терминальные функции, которые проверяют все элементы потока целиком
        // anyMatch(Predicate<T>) - хотя бы один элемент удовлетворяет
        // allMatch(Predicate<T>) - все элементы удовлетворяют
        // noneMatch(Predicate<T>) - ни один из элементов не удовлетворяет

        // убедитесь что ни один из names не содержит "y"
        System.out.println(
                Arrays.stream(names)
                        .noneMatch(s -> s.contains("y"))
        );

        // проверим что каждое из имен содержит "a"
        System.out.println(
                Arrays.stream(names)
                        .allMatch(s -> s.contains("a"))
        );


        int [] numbers = {2,4,5,8,3};

        // .reduce() - терминальная операция, которая выполняет некоторое действие
        //             над всеми элементами потока и возвращает конечный результат

        // sum элементов numbers
        System.out.println(
                Arrays.stream(numbers)
                        // 1. .reduce() with 2 parameters returns int:
                        // identity - начальное значение
                        .reduce(0, new IntBinaryOperator() {
                            @Override
                            public int applyAsInt(int prev, int current) {
                                return prev + current;
                            }
                        })
        );

        // найдите произведение элементов numbers
        System.out.println(
                Arrays.stream(numbers)
//                        .reduce(0, (left, right) -> left * right) // = 0
                        .reduce(1, (left, right) -> left * right) // = 960
        );

        // превратите names в "Alexander, Max, Xenia, Maria"
        System.out.println(
                Arrays.stream(names)
                        // 2. .reduce() with 1 parameter returns Optional:
                        .reduce(new BinaryOperator<String>() {
                            @Override
                            public String apply(String s1, String s2) {
                                return s1 + ", " + s2;
                            }
                        })
                        // .orElse("") <- решение
        ); // Optional[Alexander, Max, Xenia, Maria]

        // Optional<T> - может быть значение, но может и не быть значения

        // найдите произведение элементов numbers без начального значения
        System.out.println(
                Arrays.stream(numbers)
                        .reduce((left, right) -> left * right)
        ); // OptionalInt[960]

        // решение:
        System.out.println(
                Arrays.stream(numbers)
                        .reduce((left, right) -> left * right)
                        .orElse(0) // <- решение (если выше пустой результат, т.е. numbers пуст)
        ); // 960

        System.out.println(
                years.stream()
                        .filter(y -> y > 10_000)
                        .findFirst() // возвратить первый элемент
        ); // Optional.empty

        // min / max
        System.out.println(
                Arrays.stream(names)
                        // нужен компаратор
                        .max(Comparator.naturalOrder()) // компаратор построенный на Comparable;
                                                       // "встроенный" в тип компаратор
        ); // Optional[Xenia]

        // верните самое длинное имя
        System.out.println(
                Arrays.stream(names)
                        // .max((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                        .max(Comparator.comparingInt(String::length))
        );


        // Collect - может собрать элементы потока в разные контейнеры.

        Set<String> namesSet =
                Arrays.stream(names)
                        .collect(Collectors.toSet());

        Arrays.stream(names)
                .collect(
                        // .toCollection - тут сами указываем, какого рода коллекцию хотим (Supplier)
                        Collectors.toCollection(new Supplier<Collection<String>>() {
                            @Override
                            public Collection<String> get() {
                                return new ArrayList<>();
                            }
                        })
                        // короткая запись:
//                        Collectors.toCollection(ArrayList::new)
                );

        String namesCombined =
                Arrays.stream(names)
                        .collect(
                                Collectors.joining(", ")
                        );
        System.out.println(namesCombined); // Alexander, Max, Xenia, Maria


        // groupingBy - возможность сгруппировать элементы в Map
        // с помощью функции вычисления ключа

        // Alexander  Max  Xenia  Maria
        System.out.println(
                Arrays.stream(names)
                        .collect(
                                Collectors.groupingBy( // result: T -> Map<K, List<T>>
                                        //<input (function), output (map's key)>
                                        new Function<String, Integer>() {
                                            @Override
                                            public Integer apply(String s) {
                                                // верните длину строки
                                                return s.length();
                                            }
                                        }
                                )
                        )
        ); // {3=[Max], 5=[Xenia, Maria], 9=[Alexander]}


        // Collectors toMap
        System.out.println(
                Arrays.stream(names)
                        .collect(
                                Collectors.toMap(
                                        //<input (function), output (map's key)>
                                        new Function<String, String>() {
                                            @Override
                                            public String apply(String s) {
                                                return s;
                                            }
                                        },

                                        //<input (function), output (map's value)>
                                        new Function<String, Integer>() {
                                            @Override
                                            public Integer apply(String s) {
                                                return s.length();
                                            }

                                        }
                                )
                        )
        ); // {Alexander=9, Max=3, Maria=5, Xenia=5}

        // то же самое, короткая запись:
        System.out.println(
                Arrays.stream(names)
                        .collect(
                                Collectors.toMap(
                                        s -> s, // или: Function.identity(),
                                        String::length
                                )
                        )
        );
    }
}
