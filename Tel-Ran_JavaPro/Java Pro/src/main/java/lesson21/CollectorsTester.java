package lesson21;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsTester {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Michael", "Svetlov", 24),
                new Student("Alexander", "Svetlov", 28),
                new Student("Daria", "Petrovskaya", 19),
                new Student("Maria", "Drobysheva", 19),
                new Student("Max", "Pavlov", 24)
        );

        // Collectors
        //      сгруппировать элементы
        //      обсчитать статистику
        //      сохранить элементы потока в коллекции на ваш выбор

        // соберите студентов у которых в фамилии есть буква s в любом регистре
        // в список, потом список распечатайте List
        // filteredStudents
        Collection<Student> filteredStudents =
                students.stream()
                        .filter(s -> s.getLastName().toLowerCase().contains("s"))
//                        .collect(Collectors.toList()); // ArrayList
//                        .collect(Collectors.toSet()); // HashSet
//                        .collect(Collectors.toUnmodifiableList()); // Read-only list
//                        .collect(Collectors.toUnmodifiableSet()); // Read-only set
                          .collect(Collectors.toCollection(
                                  LinkedList::new
                          ));
        System.out.println(filteredStudents);


        // соберите уникальные фамилии студентов в
        // коллекцию по возрастанию
        // Collection<String> uniqueLastNames
        Collection<String> uniqueLastNames =
                students.stream()
                        .map(Student::getLastName)
                        .collect(Collectors.toCollection(
                                TreeSet::new
                        ));
        System.out.println(uniqueLastNames);


        // сгруппируйте студентов по гендеру
        Map<Boolean, List<Student>> studentsByGender =
                students.stream()
                        .collect(Collectors.partitioningBy(
                                s -> s.getLastName().endsWith("a")
                        ));
        System.out.println(studentsByGender);


        // разбейте студентов на тех кто старше и младше 20 лет
        Map<Boolean, List<Student>> studentsOlderThan20 =
                students.stream()
                        .collect(Collectors.partitioningBy(
                                s -> s.getAge() >= 20
                        ));
        System.out.println(studentsOlderThan20);


        // разбейте студентов на группы по возрасту
        Map<Integer, List<Student>> studentsByAge =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getAge
                        ));
        System.out.println(studentsByAge);


        // разбейте студентов по первой букве их имени
        Map<String, List<Student>> studentsByFirstNameLetter =
                students.stream()
                        .collect(Collectors.groupingBy(
                                s -> s.getFirstName().substring(0, 1)
                        ));
        System.out.println(studentsByFirstNameLetter);

        Map<Integer, Set<Student>> studentsByAgeSet =
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getAge,
                        Collectors.toSet()
                ));
        System.out.println(studentsByAgeSet);


        // сбор статистики
        Long numberOfStudents =
                students.stream()
                        // .count();
                        .collect(
                                Collectors.counting()
                        );
        System.out.println(numberOfStudents);

        Map<Integer, Long> studentsByAgeNumber =
                students.stream()
                        .collect(
                                Collectors.groupingBy(
                                        s -> s.getAge(),
                                        Collectors.counting()
                                )
                        );
        System.out.println(studentsByAgeNumber);

        DoubleSummaryStatistics ageStatistics =
                students.stream()
                        .collect(
                                Collectors.summarizingDouble(
                                        Student::getAge
                                )
                        );
        System.out.println("max age: " + ageStatistics.getMax());
        System.out.println("min age: " + ageStatistics.getMin());
        System.out.println("average age: " + ageStatistics.getAverage());
        System.out.println("count of age: " + ageStatistics.getCount());


        // определим максимум
        Optional<Student> maxAgeStudent =
                students.stream()
                        .collect(
                                Collectors.maxBy(Comparator.comparing(Student::getAge))
                        );
        System.out.println(maxAgeStudent);

        // Collectors.toMap
        Map<String, String> firstToLast =
                students.stream()
                        .collect(
                                Collectors.toMap(
                                        s -> s.getFirstName(),
                                        s -> s.getLastName()
                                )
                        );
        System.out.println(firstToLast);

        Map<String, Integer> nameToAge =
                students.stream()
                        .collect(Collectors.toMap(
                                s -> s.getFirstName() + " " + s.getLastName(),
                                Student::getAge
                        ));
        System.out.println(nameToAge);


        System.out.println(studentsByGender); // Map<Integer, List<Student>>

        System.out.println(
                studentsByGender.entrySet().stream()
                        .map(pair -> new AbstractMap.SimpleEntry<>(pair.getValue(), pair.getKey()))
                        .collect(Collectors.toMap(
                                pair -> pair.getKey(),
                                pair -> pair.getValue()
                        ))
        );


        // создайте из мапы  Map<Boolean, List<Student>> studentsByGender
        // Map<Boolean, Double> genderToAverageAge
        System.out.println(
                studentsByGender.entrySet().stream()
                        .map(pair -> new AbstractMap.SimpleEntry<>(
                                pair.getKey(),
                                pair.getValue().stream()
                                        .mapToDouble(Student::getAge)
                                        .average().orElse(0)
                        ))
                        .collect(Collectors.toMap(
                                pair -> pair.getKey(),
                                pair -> pair.getValue()
                        ))
        );


        // joining
        System.out.println(
                // Daria, Max ... -> {Daria, Max, ... }
                students.stream()
                        .map(s -> s.getFirstName())
                        .collect(
                                Collectors.joining(", ", "{", "}")
                        )
        );
    }
}
