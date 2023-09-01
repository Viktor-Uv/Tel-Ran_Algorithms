package lesson19;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmpTester {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Max Petrov", 22, "programmer"),
                new Employee("Ivan Shapovalov", 33, "analyst"),
                new Employee("Semen Deznev", 55, "manager"),
                new Employee("Oleg Petrov", 19, "intern"),
                new Employee("Katerina Drogova", 31, "programmer"),
                new Employee("Nicolas Spivakov", 23, "analyst"),
                new Employee("Boris Moiseev", 48, "manager"),
                new Employee("Petr Sveshnikov", 37, "programmer"),
                new Employee("Alex Con", 33, "analyst"),
                new Employee("Olga Filimonova", 27, "programmer")
        );

        // найдите самого молодого
        System.out.println(
                employees.stream()
                        .min(Comparator.comparingInt(Employee::getAge)) // -> returns Optional, so:
                        .orElse(null)

                        // .ifPresent - special lambda
                        // .ifPresent(System.out::println)
        );

        // посчитайте количество различных позиций
        System.out.println(
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getPosition
                        ))
                        .size()
        );
        // второй вариант:
        System.out.println(
                employees.stream()
                        .map(Employee::getPosition) // создать новый поток из "позиций"
                        .distinct() // оставить только уникальные
                        .count()
        );
    }
}
