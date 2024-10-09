package lesson13;

import java.util.Comparator;

public class Student {
    private String name;
    private int age;
    private float height;

    public Student(String name, int age, float height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "S{" +
                "n='" + name + '\'' +
                ", a=" + age +
                ", h=" + height +
                '}';
    }

    public static Comparator<Student> ageComparator = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s1.getAge(), s2.getAge());
        }
    };

    public static String id = new String("123456");

    public static Comparator<Student> nameComparator = (s1, s2) -> s1.getName().compareTo(s2.getName());

//    public static Comparator<Student> heightComparator = new Comparator<Student>() {
//        @Override
//        public int compare(Student s1, Student s2) {
//            return Float.compare(s1.getHeight(), s2.getHeight());
//        }
//    };

//    public static Comparator<Student> heightComparator = (s1, s2) -> Float.compare(s1.getHeight(), s2.getHeight());

    public static Comparator<Student> heightComparator = Comparator.comparing(Student::getHeight);
}
