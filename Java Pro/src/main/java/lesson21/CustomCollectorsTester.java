package lesson21; // Date 06.09.2023

// Collector<T,A,R>
// T - тип из входного потока
// A - тип аккумулятора
//      аккумулятор - контейнер, в котором накапливаются результаты
//      обработки элементов из входного потока
// R - тип возвращаемого значения
// supplier - метод, который возвращает пустой аккумулятор
// accumulator - сохраняет в A (аккумулятор) данные из каждого входного элемента
// combiner - объединяет A (аккумуляторы) в один
// finisher - преобразует A в результат

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomCollectorsTester {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Michael", "Svetlov", 24),
                new Student("Alexander", "Svetlov", 28),
                new Student("Daria", "Petrovskaya", 19),
                new Student("Maria", "Drobysheva", 19),
                new Student("Max", "Pavlov", 24)
        );

        // напишем коллектор который суммирует возраста студентов
        //        вход     аккумулятор    результат
        Collector<Student, List<Integer>, Integer> studentAgeCollector = new Collector<Student, List<Integer>, Integer>() {
            // supplier() создает аккумулятор в котором будут накапливаться промежуточные результаты
            @Override
            public Supplier<List<Integer>> supplier() {
                // return () -> new ArrayList<>();
                return ArrayList::new; // создание пустого контейнера для аккумуляции
            }

            // accumulator() должна как-то обработать и зафиксировать данные
            // каждого входного элемента в аккумуляторе
            @Override
            public BiConsumer<List<Integer>, Student> accumulator() {
                return (integers, student) -> integers.add(student.getAge());
            }

            // объединяет аккумуляторы между собой
            // при параллельной работе в разных потоках выполнения
            // по окончании обработки всех входных элементов
            @Override
            public BinaryOperator<List<Integer>> combiner() {
                // добавьте данные из второго листа в первый
                // и возвратите первый
                return (integers1, integers2) -> {
                    integers1.addAll(integers2);
                    return integers1;
                };
            }

            //
            @Override
            public Function<List<Integer>, Integer> finisher() {
                // сложить все числа в списке между собой
                // и вернуть сумму
                return integers -> integers.stream()
                        .reduce(0, Integer::sum);
            }

            // возвращает параметры коллектора
            @Override
            public Set<Characteristics> characteristics() {
                // Characteristics.IDENTITY_FINISH - не нужно запускать finisher()
                // Characteristics.CONCURRENT - входные элементы можно аккумулировать в
                //      нескольких параллельных потоках выполнения
                // Characteristics.UNORDERED - данные могут обрабатываться в произвольном порядке

//                return new HashSet<>(Arrays.asList(Characteristics.CONCURRENT));
                return Set.of(Characteristics.UNORDERED); // безопасный вариант
            }
        };

        // запустите Collector для потока студентов и
        // распечатайте результат
        System.out.println(
                students.stream()
                        .collect(studentAgeCollector)
        );


        List<String> names = Arrays.asList("Max", "Lena", "Sveta", "Alexander");
        // посчитайте кастомным коллектором их суммарную длину


    }
}
