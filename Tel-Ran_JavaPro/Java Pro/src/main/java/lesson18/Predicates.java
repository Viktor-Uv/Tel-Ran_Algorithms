package lesson18;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html

public class Predicates {
    public static void main(String[] args) {
        // Predicate
        // функциональный интерфейс который принимает значение любого типа
        // и возвращает boolean
        // тест на соответствие значения какому-нибудь условию
        // можно объединять между собой с помощью and negate or

        List<String> names = Arrays.asList("John", "Max", "Alexander", "Vasilisa");

        // напишите предикат, который возвратит true только если строка длиннее 5 символов
        Predicate<String> moreThan5Letters = s -> s.length() > 5;

        // предикат, который вернет true если в строке есть буква x
        Predicate<String> containsX = s -> s.contains("x");

        System.out.println(
                names.stream() // создаем поток из коллекции
                        .filter(containsX) // пропускает только соответствующие предикату элементы
                        .collect(Collectors.toList()) // собирает все элементы в List
        );

        // "сложный" предикат, который пропускает как элементы длиннее 5 символов,
        // так и элементы, содержащие 'x'
        Predicate<String> complexPredicate = moreThan5Letters.or(containsX);

        // обработайте names c помощью complexPredicate и выведите результат на экран
        System.out.println(
                names.stream() // создаем поток из коллекции
                        .filter(complexPredicate) // пропускает только соответствующие предикату элементы
                        .collect(Collectors.toList()) // собирает все элементы в List
        );

    }
}
