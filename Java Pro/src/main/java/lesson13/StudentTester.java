package lesson13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StudentTester {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(
                Arrays.asList(
                        new Student("Volkov", 24, 1.72f),
                        new Student("Sergeev", 22, 1.68f),
                        new Student("Sivkov", 22, 1.82f),
                        new Student("Solovyev", 21, 1.61f),
                        new Student("Volkogonov", 21, 1.74f),
                        new Student("Smirnov", 20, 1.85f),
                        new Student("Seliverstov", 22, 1.77f)
                )
        );

        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getAge(), s2.getAge());
            }
        });

        System.out.println(students);

        students.sort(Student.ageComparator.thenComparing(Student.nameComparator));
        System.out.println(students);

        // сортировка "наоборот"
        // отсортируйте студентов по убыванию имен любыми способами
        // посмотрите на реализацию компаратора для имен
        Comparator<Student> reverseNameComparator = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s2.getName().compareTo(s1.getName());
            }
        };
        students.sort(reverseNameComparator);
        System.out.println(students);

    }
}
