package lesson5;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class BinarySearchTest {
    public static void main(String[] args) {
        int [] r = {1, 4, 14, 33, 37, 41, 50};
        // binary search requires already sorted container

        // 0 1 2  3   4 5  6
        // 1,4,14,33,37,41,50     41 -> 5   O(n) ~ log(N)
        // 1,4,14,33,37,41,50     46
        // 1,4,14,33,37,41,46,50  46   -6-1=-7

        System.out.println(
                Arrays.binarySearch(r, 41) // 5
        );

        System.out.println(
                Arrays.binarySearch(r, 46) // -7
        );

        System.out.println(
                Arrays.binarySearch(r, -12) // -1
        );

        String capitals [] = {"Berlin", "Warsaw", "Prague", "Riga", "Budapest"};
        Arrays.sort(capitals);
        System.out.println(
                Arrays.toString(capitals) + "; Lisbon: " +
                Arrays.binarySearch(capitals, "Lisbon") // -3
        );



    }

}
