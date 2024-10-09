package lesson4;

import java.util.Arrays;
import java.util.Random;

/**
 * Algorithms. Homework #4
 *
 * @author Viktor Uvarchev
 * @version 27 Jul 2023
 */

public class Homework4 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[rand.nextInt(0,15)];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(0, 100);
        }
        System.out.println("before: " + Arrays.toString(array));
        mergeSort(array);
        System.out.println(" after: " + Arrays.toString(array));
    }

    public static void mergeSort(int[] input) {
        // Base case: one-element array is always sorted
        if (input.length < 2) {
            return;
        }
        // Split arrays and recursively merge-sort both parts
        int[] leftPart= Arrays.copyOfRange(input, 0, input.length/2);
        int[] rightPart = Arrays.copyOfRange(input, input.length/2, input.length);
        mergeSort(leftPart);
        mergeSort(rightPart);
        // Merge-sort arrays together
        for (int i = 0, iLeft = 0, iRight = 0; i < input.length; i++) {
            if (iLeft < leftPart.length && iRight < rightPart.length) {
                if (leftPart[iLeft] <= rightPart[iRight]) {
                    input[i] = leftPart[iLeft++];
                } else {
                    input[i] = rightPart[iRight++];
                }
            } else {
                if (iLeft >= leftPart.length) {
                    input[i] = rightPart[iRight++];
                } else {
                    input[i] = leftPart[iLeft++];
                }
            }
        }
    }
}
