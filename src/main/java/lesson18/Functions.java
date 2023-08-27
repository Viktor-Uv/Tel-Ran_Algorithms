package lesson18;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

// https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html
// https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html

public class Functions {
    public static void main(String[] args) {
        // Function
        // https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html
        // принимает на вход элемент одного типа, а отдает элемент другого типа
        // могут объединяться andThen и compose

        // для строки возвращать ее длину
        //         in  ,   out
        Function<String, Integer> stringIntegerFunction = s -> s.length();
        List<String> names = Arrays.asList("John", "Max", "Alexander", "Vasilisa");

        System.out.println(
                names.stream()
                        // .map(stringIntegerFunction)
                        .map(s -> s.length())
                        .collect(Collectors.toList())
        );

        // напишите Function который добавит к строке одинарные кавычки
        // alex -> 'alex'
        Function<String, String> quote = s -> "'" + s + "'";

        Function<Double, String> formatDouble = d -> "double: " + d;

        // Double -> String -> String - combining lambdas

        System.out.println( // 'double: 3.14'
                formatDouble.andThen(quote).apply(3.14)
        );
        System.out.println( // 'double: 3.14'
                quote.compose(formatDouble).apply(3.14)
        );

        // Identity
        Function<Long, Long> longIdentity = Function.identity();
        System.out.println(longIdentity.apply(3L));

        // BiFunction
        // https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html
        // функция, которая принимает на вход два аргумента разных типов
        // и возвращает значение другого типа
        BiFunction<String, Double, Integer> formatter = (s, d) -> (s + " " + d).length();
        System.out.println(formatter.apply("hello", 3.14));

        Map<String, Integer> salaries = new HashMap<>();
        salaries.put("John", 15_000);
        salaries.put("Masha", 25_000);
        salaries.put("Alex", 17_000);
        salaries.put("Samuel", 33_000);

        salaries.computeIfAbsent("Max", s -> 4000 * s.length());
        System.out.println(salaries);

        // увеличьте зарплату каждого на 1200
        salaries.replaceAll(
                (name, salary) -> salary + 1200
        );
        System.out.println(salaries);

        // увеличьте зарплату Max в 2 раза
        // зарплату всех остальных не меняем
        salaries.replaceAll(
                (name, salary) -> name.equals("Max") ? salary * 2 : salary
        );
        System.out.println(salaries);
    }
}
