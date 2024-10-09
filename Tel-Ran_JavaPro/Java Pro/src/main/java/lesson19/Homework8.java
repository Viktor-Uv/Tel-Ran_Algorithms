package lesson19;

import java.util.*;
import java.util.stream.Collectors;

/**
 * JavaPro. Homework #8
 *
 * @author Viktor Uvarchev
 * @version 01 Sep 2023
 */

/*
 * Task 1. распечатайте фамилии всех женщин - оканчивающиеся на "a" (Filimonova, Drogova);
 * Task 2. распечатайте только имена всех работников - имя это то, что до пробела;
 * Task 3. найдите средний возраст;
 * Task 4. посчитайте количество программистов мужчин (name не оканчивается на "a");
 * Task 5. посчитайте сумму возрастов работников;
 * Task 6. разделите всех работников на 2 группы - старше 40 лет (true) и младше 40 лет (false);
 * Task 7. найдите профессию самого старшего из "молодых" - тех кому <= 40 лет;
 * Task 8. сгруппируйте всех работников по профессии;
 * Task 9. посчитаем количество людей в профессии;
 * Task 10. Вернуть средний возраст мужчин и женщин - у женщин фамилия оканчивается на "a" -
 *          в виде Map - ключ "true" соответствует женщинам;
 * Task 11. Распечатать работников с самым часто встречающимся возрастом.
 */

public class Homework8 {
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

        System.out.println("Result of Task 1: " +
                employees.stream()
                        .map(e -> e.getName().split(" ")[1])
                        .filter(s -> s.endsWith("a"))
                        .collect(Collectors.joining(", "))
        );

        System.out.println("Result of Task 2: " +
                employees.stream()
                        .map(employee -> employee.getName().split(" ")[0])
                        .collect(Collectors.joining(", "))
        );

        System.out.println("Result of Task 3: " +
                employees.stream()
                        .mapToInt(Employee::getAge)
                        .average()
                        .orElse(0)
        );

        System.out.println("Result of Task 4: " +
                employees.stream()
                        .filter(e -> e.getPosition().equals("programmer")) // correction
                        .map(e -> e.getName().split(" ")[1])
                        .filter(s -> !s.endsWith("a"))
                        .count()
        );

        System.out.println("Result of Task 5: " +
                employees.stream()
                        .mapToInt(Employee::getAge)
                        .reduce(0, Integer::sum)
        );

        System.out.println("Result of Task 6: " +
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        e -> e.getAge() > 40
                                )
                        )
        );

        System.out.println("Result of Task 7: " +
                employees.stream()
                        .filter(e -> e.getAge() <= 40)
                        .max(Comparator.comparingInt(Employee::getAge))
                        .map(Employee::getPosition)
                        .orElse(null)
        );

        System.out.println("Result of Task 8: " +
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getPosition
                        ))
        );

        System.out.println("Result of Task 9: " +
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getPosition,
                                Collectors.counting()
                        ))
        );

        System.out.println("Result of Task 10: " +
                employees.stream()
                        .collect(Collectors.groupingBy(
                                e -> e.getName().endsWith("a"), // optimisation
                                Collectors.averagingDouble(Employee::getAge)
                        ))
        );

        System.out.println("Result of Task 11: " +
                employees.stream()
                        // Get list of Employees grouped by their age
                        .collect(Collectors.groupingBy(
                                Employee::getAge
                        ))
                        // Get Map.Entry set
                        .entrySet().stream()
                        // Find most popular age by the size of the list value
                        .max(Comparator.comparingInt(e -> e.getValue().size()))
                        // Extract only the value to display
                        .map(Map.Entry::getValue)
                        .orElse(null)
        );

    }
}
