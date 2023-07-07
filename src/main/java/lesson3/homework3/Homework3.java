package lesson3.homework3;

import java.util.Arrays;

/**
 * Algorithms. Homework #3
 *
 * @author Viktor Uvarchev
 * @version 05 Jul 2023
 */

/*
 * 1. Написать рекурсивный метод получения чисел Фибоначчи
 *
 * 2*. Ханойские башни - решить интерактивно и рекурсивно
 */

public class Homework3 {
    public static void main(String[] args) {
        // Task 1
        System.out.println(Arrays.toString(fibonacciRecursion(10)));
    }

    // Function for Task 1
    private static int[] fibonacciRecursion(int n) {
        if (n == 2) { // Handle base case
            return new int[]{0, 1};
        } else {
            int[] result = fibonacciRecursion(n - 1); // Solve size n-1
            result = Arrays.copyOf(result, n); // Extend array
            result[n - 1] = result[n - 2] + result[n - 3]; // Solve sum of 2 previous elements
            return result;
        }
    }
}
