package lesson5;

import java.util.Arrays;
import java.util.Comparator;

public class SortTester {
    public static void main(String[] args) {
        int [] numbers = {10, 20, 5, -40, 8};
        Arrays.sort(numbers); // N*log(N)
        System.out.println(
                Arrays.toString(numbers)
        );

        String capitals [] = {"Berlin", "Warsaw", "Prague", "Riga", "Budapest"};
        Arrays.sort(capitals, Comparator.reverseOrder());
        System.out.println(Arrays.toString(capitals));



    }
}
