package lesson5;

import java.util.Arrays;
import java.util.Random;

/**
 * Algorithms. Homework #5
 *
 * @author Viktor Uvarchev
 * @version 03 Aug 2023
 */

public class Homework5 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[rand.nextInt(0,15)];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(0, 100);
        }
        System.out.println("before: " + Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println(" after: " + Arrays.toString(array));
        int[] test = Arrays.copyOf(array, array.length);
        Arrays.sort(test);
        System.out.println("  test: " + Arrays.toString(test));
    }

    public static void quickSort(int[] array, int left, int right) {
        // Base case
        if (right <= left) {
            return;
        }

        // Split array, sort two parts and return split point
        int pivot = partition(array, left, right);

        // Repeat recursively for the left side
        quickSort(array, left, pivot - 1);
        // Repeat recursively for the right side
        quickSort(array, pivot + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        // Select last element as pivot
        int pivot = array[right];
        int i = left - 1;

        // Sort
        for (int j = left; j <= right; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        i++;

        // Put pivot between two sorted parts
        swap(array, i, right);

        // Return split position
        return i;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
}
