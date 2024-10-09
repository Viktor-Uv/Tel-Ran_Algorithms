package lesson20; // Date 30.08.2023

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lesson20 {
    public static void main(String[] args) {
        // Java Streams: Flat map.
        // filter n -> 0..n
        // map n -> n
        // flatMap n -> 0..∞

        Integer[][] twoDimensionalArray = {
                {1, 2, 3},
                {5, 6, 8, 10},
                {11},
                {14, 12}
        };

        System.out.println(Arrays.deepToString(twoDimensionalArray)); // [[1, 2, 3], [5, 6, 8, 10], [11], [14, 12]]

        Arrays.stream(twoDimensionalArray)
                .forEach(array -> System.out.println(Arrays.toString(array))); // dimensions line-by-line

        System.out.println("===flatMap===");
        Arrays.stream(twoDimensionalArray)
//                .flatMap(new Function<Integer[], Stream<Integer>>() {
//                    @Override
//                    public Stream<Integer> apply(Integer[] integers) {
//                        return Arrays.stream(integers);
//                    }
//                })
//                or:
//                .flatMap(integers -> Arrays.stream(integers))
//                or:
                .flatMap(Arrays::stream) // уплощение массива
                .forEach(System.out::println); // each integer on its own line

        List<List<String>> names = Arrays.asList(
                Arrays.asList("Masha", "Alexander"),
                Arrays.asList("Semen", "Vlad", "Alice", "Xenia")
        );
        // преобразуйте в поток имен и распечатайте
        System.out.println("===names===");
        names.stream()
                .flatMap(strings -> strings.stream())
                .forEach(System.out::println);

        // преобразовать names в поток длин имен
        // желательно за одну операцию flatMap
        System.out.println("===names-length===");
        names.stream()
                .flatMap(strings -> strings.stream()
                        .map(String::length))
                .forEach(System.out::println);

        // преобразуйте names в поток букв
        // M a s h a A l e x ...
        System.out.println("===names-letters===");
        System.out.println(
                names.stream()
                        .flatMap(strings -> strings.stream())
                        // .flatMap(name -> Arrays.stream(name.toCharArray())) - wrong, can't give array of chars
                        .flatMap(name -> Arrays.stream(name.split(""))) // - split делает массив из стрингов,
                                                                              // а т.к. стринги ссылочного типа - то
                                                                              // можно использовать Arrays.stream(),
                                                                              // чтобы сделать из него поток
                        .collect(Collectors.joining(" "))
        );


        Order grocery = new Order(
                new OrderItem("mango", 2, 1.66),
                new OrderItem("apples", 3, 0.99),
                new OrderItem("corona", 2, 1.35)
        );

        Order utility = new Order(
                new OrderItem("water", 104, 0.30),
                new OrderItem("electricity", 201, 0.38)
        );

        List<Order> orders = Arrays.asList(grocery, utility);

        // посчитайте суммарные затраты в orders
        System.out.println("===orders-sum===");
        System.out.println(
                orders.stream()
                        .flatMap(order -> order.getItems().stream()
                                .map(item -> item.getQuantity() * item.getUnitPrice()))
                        .reduce(.0, Double::sum)
        );


        List<Book> library = Arrays.asList(
                new Book("War and Peace", "Max", "Sveta", "Dima"),
                new Book("Movable feast", "Ernest", "George"),
                new Book("Hello to Spartans", "Dima", "Alexander", "Galina")
        );

        // распечатайте всех авторов
        System.out.println("===books===");
        library.stream()
                .flatMap(book -> book.getAuthors().stream())
                .forEach(System.out::println);

        // распечатайте неповторяющихся авторов в порядке возрастания имен
        System.out.println("===books-distinct-sorted===");
        library.stream()
                .flatMap(book -> book.getAuthors().stream())
//                .distinct() // remove duplicates
//                .sorted() // sort
//                .forEach(System.out::println);
        // or:
                .collect(Collectors.toCollection(TreeSet::new))
                .forEach(System.out::println);


        int[][] ints = {
                {1,2,3},
                {4}
        };
//        Arrays.stream(ints).flatMap(array -> Arrays.stream(array)).toArray() // - doesn't work, as
        // .flatMap() doesn't have instances to work with primitives. Instead, use .flatMapTo...
        System.out.println("===primitive===");
        System.out.println(
                Arrays.toString(
                        Arrays.stream(ints)
                                .flatMapToInt(array -> Arrays.stream(array))
                                .toArray()
                )
        );


        // создайте на базе names
        // Map<Integer, List<String>> ключом которой была бы длина имени
        // с помощью collect
        // Collectors.groupingBy()
        // распечатайте результат
        System.out.println("===map===");
        System.out.println(
                names.stream()
                        .flatMap(list -> list.stream())
                        .collect(Collectors.groupingBy(name -> name.length()))
                // Map<Integer>, List<String>>
        );

        System.out.println("===map-with-count==="); // from one map make another map
        System.out.println(
                names.stream()
                        .flatMap(list -> list.stream())
                        .collect(Collectors.groupingBy(name -> name.length()))
                        .entrySet().stream() // Pair<Integer, List<String>>
                        .map(pair -> new AbstractMap.SimpleEntry<Integer, Integer>(
                                pair.getKey(), pair.getValue().size()
                        ))
                        .collect(Collectors.toMap(
                                AbstractMap.SimpleEntry::getKey,
                                AbstractMap.SimpleEntry::getValue
                        ))
                // Map<Integer>, List<String>> -> Map<Integer, Integer>
        );







    }
}