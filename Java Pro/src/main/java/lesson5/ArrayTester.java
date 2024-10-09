package lesson5;

import java.util.Arrays;

public class ArrayTester {
    public static void main(String[] args) {
        int [] a = new int[]{1, 7, 5, 3, -20}; // 5 элементов
        int b [] = new int[2]; // массив из двух элементов со
        // значениями по-умолчанию для типа

        // создайте массив строк movies
        // со значениями "Apocalypse now", "Taxi Driver", "Tango and Cache"

        //                               0                 1              2
        String [] movies = new String[] {"Apocalypse now", "Taxi Driver", "Tango and Cache"};
        String [] groups = {"Guns and Roses", "Aerosmith", "Rolling Stones"};

        System.out.println("groups length: " + groups.length); // длина массива

        System.out.println(movies);

        System.out.println(
                Arrays.toString(movies)
        );

        System.out.println(movies[2]); // обращение к элементу
        movies[2] = "Hateful Eight"; // изменение значения элемена масива

        System.out.println(
                Arrays.toString(movies)
        );

        // movies[3] = "Space Odyssey";
        // нет возможности увеличить или уменьшить размер массива после создания

        System.out.println(movies);
        System.out.println(Arrays.toString(movies));
        movies = new String[] {"Save private Ryan"};
        System.out.println(movies);
        System.out.println(Arrays.toString(movies));

        for(int i = 0; i < groups.length; i++)
        {
            System.out.println("groups " + i + " " + groups[i]);
        }

        System.out.println(
                format(a, "<", ", ", ">")
        );

        System.out.println(
                multiply(a)
        );

        // можно ли в каком-либо массиве хранить и 1.33 и 23 и "Dima" и true
        Object [] objects = {1.33, 23, "Dima", true};

        int[] q = {1, 2, 3, 4, 5};
        //reverse(q);
        //System.out.println(Arrays.toString(q));

        System.out.println(compare(q, new int [] {1, 2, 3, 4, 5}));


    } // окончание main

    public static boolean compare(int [] a, int [] b) {
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
    }

    public static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }

    public static int multiply(int [] c) {
        int m = 1;
        for (int element : c) {
            m *= element;
        }
        return m;
    }

    //        prefix    delim   suffix
    // {1,2}, "<",      ", ",   ">"     ->     "<1, 2>"
    public static String format(int[] a, String prefix, String delimiter, String suffix) {
        String r = prefix;
        for (int i = 0; i < a.length; i++) {
            if(i != 0) {
                r += delimiter;
            }
            r += a[i];
        }
        r += suffix;
        return r;
    }
}
