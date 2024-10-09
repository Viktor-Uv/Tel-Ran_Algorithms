package lesson26.string;

import java.util.Random;

public class StringTester {
    public static void main(String[] args) {
        String max = "Max";
        String anotherMax = "Max";
        String max2 = new String("Max");
        System.out.println(anotherMax == max); // true | false
        System.out.println(max == max2); // false - сравнение происходит по адресу для ссылочных типов
        System.out.println(max.equals(max2)); // true

        // String - final and immutable
        String name = "Anna";
        name += " "; // original 'name' lost its link and new Object 'name' is created
        name += "Vasilieva"; // same again, 2 objects 'name' lost their links

        StringBuilder b = new StringBuilder("Anna");
        b.append(" ");
        b.append("Vasilieva");
        System.out.println(b);

        concatManyDoubleUsingString(); // 11536
        concatManyDoubleUsingStringBuilder(); // 32

        // лучше использовать в многопоточных приложения
        StringBuffer sbf = new StringBuffer();

    } // Main

    // переделайте на StringBuilder
    public static void concatManyDoubleUsingStringBuilder() {
        long before = System.currentTimeMillis();
        String r = "";
        Random random = new Random();
        // напишите цикл for который 100_000 раз получает новый рандомный double и
        // добавляет его в r
        StringBuilder sb = new StringBuilder(r);
        for (int i = 0; i < 100_000; i++) {
            sb.append(random.nextDouble());
        }
        long after = System.currentTimeMillis();
        System.out.println("time concatManyDoublesUsingString: " + (after - before));
    }

    public static void concatManyDoubleUsingString() {
        long before = System.currentTimeMillis();
        String r = "";
        Random random = new Random();
        // напишите цикл for который 100_000 раз получает новый рандомный double и
        // добавляет его в r
        for (int i = 0; i < 100_000; i++) {
            r += random.nextDouble();
        }
        long after = System.currentTimeMillis();
        System.out.println("time concatManyDoublesUsingString: " + (after - before));
    }
}
