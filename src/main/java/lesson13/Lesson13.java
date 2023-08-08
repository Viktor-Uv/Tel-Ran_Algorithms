package lesson13;

import java.util.*;

public class Lesson13 {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println(b > a); // true
        System.out.println(b - a); // 1

        String s1 = new String("value");
        String s2 = new String("value");
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s1); // true
        System.out.println(s2 == s2); // true
        System.out.println(s1.equals(s2)); // true

        Cat c1 = new Cat("Barsik", 4);
        Cat c2 = new Cat("Barsik", 4);
        System.out.println(c1 == c2); // false
        System.out.println(c1.equals(c2)); // true

        List<String> groups = new ArrayList<>(
                Arrays.asList("Guns and Roses", "Aerosmith", "Led Zeppelin",
                        "Pearl Jam", "Beatles", "Cranberries")
        );
        Collections.sort(groups);
        System.out.println(groups);

        List<Cat> cats = new ArrayList<>(Arrays.asList(c1, c2));

        Cat c3 = new Cat("Baun", 12);
        Cat c4 = new Cat("Masha", 5);
        cats.add(c3);
        cats.add(c4);
        Collections.sort(cats);
        System.out.println(cats);

        Collections.sort(cats, new CatNameComparator());
        System.out.println(cats);

        // отсортируйте группы по третьей букве названия
        // "".substring()
        Comparator<String> thirdLetterComparator =
                (s11, s21) -> s11.substring(2, 3).compareTo(s21.substring(2, 3));

        Collections.sort(groups, thirdLetterComparator);
        System.out.println(groups);

        Collections.sort(groups, thirdLetterComparator.reversed());
        System.out.println(groups);

        // отсортируйте группы по длине строки
        // постарайтесь использовать лямбду
        Collections.sort(
                groups,
                (r1, r2) -> Integer.compare(r1.length(), r2.length())
        );
        System.out.println(groups);

    }

}
