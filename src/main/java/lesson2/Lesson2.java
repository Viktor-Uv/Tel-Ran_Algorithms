package lesson2;

import java.util.Arrays;

public class Lesson2 {
    public static void main(String[] args) {
        int[] array = {1, 4, 5, 6, 9};

        // Algorithm complexity O(1)
        int a = array[1];

        // Algorithm complexity O(n)
        int sum = 0;
        for (int item : array) {
            sum += item;
        }
        System.out.println(Arrays.toString(array));
        System.out.println(sum);

        // Algorithm complexity O(log n)
        System.out.println(Arrays.binarySearch(array, 1));

        // Algorithm complexity O(n * log n)
        // сортировка слиянием и быстрая сортировка

        // Algorithm complexity O(n^2)
        // пузырьковая сортировка

        // Algorithm complexity O(n!)
        // задача коммивояжёра (very slow)


    }
}
