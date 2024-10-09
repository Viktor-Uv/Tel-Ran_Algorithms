package lesson14;

import java.util.*;

public class University {
    public static void main(String[] args) {
        Student s1 = new Student("Max Kotkov", 2, "123");
        Student s2 = new Student("Nina Kopylova", 1, "444");
        Student s3 = new Student("Semen Dejnev", 5, "33");
        Student s4 = new Student("Ivan Sergeev", 1, "444");
        Student s5 = new Student("Maria Pavlova", 3, "F-33");

        Professor p1 = new Professor(
                "Max Grogoriev",
                "physics",
                new ArrayList<>(Arrays.asList(s1,s2,s3))
        );

        Professor p2 = new Professor(
                "Olga Dergunova",
                "math",
                new ArrayList<>(Arrays.asList(s4,s5))
        );

        Professor p3 = new Professor(
                "Anna Pavlova",
                "ballet",
                new ArrayList<>(Arrays.asList(s2,s5))
        );

        List<Professor> professors = new ArrayList<>(Arrays.asList(p1, p2, p3));
        System.out.println(professors); // [Max Grogoriev, Olga Dergunova, Anna Pavlova]

        // отсортируйте профессоров по количеству студентов
        Comparator<Professor> noOfStudents = Comparator.comparingInt(p12 -> p12.getStudents().size());
        Collections.sort(professors, noOfStudents);
        System.out.println(professors); // [Olga Dergunova, Anna Pavlova, Max Grogoriev]

        // распечатайте "максимального" профессора по критерию максимальный курс студента
        Comparator<Student> studentYears = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getYear(), s2.getYear());
            }
        };
        Comparator<Professor> maxStudentYear = new Comparator<Professor>() {
            @Override
            public int compare(Professor r1, Professor r2) {
                // найти макс возраст студента у 1
                int y1 = Collections.max(r1.getStudents(), studentYears).getYear();
                // найти макс возраст студента у 2
                int y2 = Collections.max(r2.getStudents(), studentYears).getYear();
                return Integer.compare(y1, y2); // сравнить
            }
        };
        System.out.println(Collections.max(professors, maxStudentYear)); // Max Grogoriev


    }
}
