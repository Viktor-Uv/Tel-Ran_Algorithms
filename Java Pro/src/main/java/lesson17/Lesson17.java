package lesson17; // Date 21.08.2023

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">Package java.util.function</a>
 * Functional interfaces provide target types for lambda expressions and method references.
 */

public class Lesson17 {
    public static void main(String[] args) {
        NoArgsReturnDouble returns10 = new NoArgsReturnDouble() {
            @Override
            public double produce() {
                return 10;
            }
        };

        // Same thing:
        NoArgsReturnDouble returns5 = () -> 5;
        NoArgsReturnDouble returns15 = () -> {
            return 15;
        };

        // напишите в виде лямбды реализацию NoArgsReturnDouble которая
        // возвращает рандомное значение double
        // Random r = new Random();
        // r.nextDouble(); // от 0 до 1
        NoArgsReturnDouble randomDouble = () -> new Random().nextDouble();

        System.out.println("Random double: " + randomDouble.produce());
        // Supplier - безаргументный функциональный интерфейс который
        // просто возвращает значение

        // Predicate - функциональный интерфейс который принимает на вход значение
        // и возвращает boolean

        // создайте в виде лямбды реализацию TwoIntsReturnDouble
        // которая проверяет что передаваемые числа друг другу равны
//        TwoIntsReturnsBoolean equality = new TwoIntsReturnsBoolean() {
//            @Override
//            public boolean check(int i, int j) {
//                return i == j;
//            }
//        };
        TwoIntsReturnsBoolean equality = (i, j) -> i == j;
        // Делится ли одно число на другое без остатка
        TwoIntsReturnsBoolean isDividedBy = (i, j) -> i % j == 0;

        // equality и isDividedBy - функциональные интерфейсы
        System.out.println(process(2,3, equality)); // false
        System.out.println(process(12,3, isDividedBy)); // true

        List<Integer> digits = Arrays.asList(1, 2, 3, 4, 8, 10, 12, 0, 2);
        System.out.println(
//                filter(
//                        digits,
//                        new IntReturnsBoolean() {
//                            @Override
//                            public boolean check(int i) {
//                                return i % 2 == 1;
//                            }
//                        }
//                )
                filter(
                        digits,
                        i -> i % 2 == 1
                )
        );

        // отфильтруйте тот-же список чтобы вывелись числа больше или равные 10
        System.out.println(
                filter(
                        digits,
                        i -> i >= 10
                )
        );

        List<String> names = Arrays.asList("Max", "Masha", "Alexander", "Dima");
        // примените map к этому списку
        // в качестве логики StringReturnInt возвращайте длину строки
        System.out.println(
                map(
                        names,
//                        new StringReturnInt() {
//                            @Override
//                            public int process(String s) {
//                                return s.length();
//                            }
//                        }
                        s -> s.length()
                )
        );

        System.out.println(
                names.stream()
                        .filter(s -> s.length() > 4) // Masha Alexander
                        .map(s -> s.length()) // 5 9
                        .collect(Collectors.toList())
        );

        // функциональный интерфейс, который принимает один тип и возвращает другой
        // (кроме boolean) называется Function

    } // Main

    static List<Integer> map(List<String> string, StringReturnInt p) {
        List<Integer> result = new ArrayList<>();
        for (String s : string) {
            result.add(p.process(s));
        }
        return result;
    }

    static List<Integer> filter(List<Integer> list, IntReturnsBoolean p)
    {
        List<Integer> output = new ArrayList<>();
        // отфильтруйте список,
        // добавляйте в выходной список только те элементы из входного
        // которые соответствуют предикату IntReturnsBoolean
        for (int i : list) {
            if (p.check(i)) {
                output.add(i);
            }
        }
        return output;
    }

    static boolean process(int i, int j, TwoIntsReturnsBoolean c) {
        // напишите реализацию - вызывать функцию из
        // интерфейса для i и j
        return c.check(i, j);
    }
}
