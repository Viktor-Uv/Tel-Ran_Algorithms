package lesson3;

import java.util.Arrays;

/**
 * Algorithms lesson #3
 * @version 29 Jun 2023
 * @see <a href="https://progoschool.ru/java/recursion">progoschool</a>
 */

public class Lesson3 {
    public static void main(String[] args) {
        System.out.println(sumNaturalIteration(8));
        System.out.println(sumNaturalRecursive(8));
        System.out.println(factorialIteration(5));
        System.out.println(factorialRecursion(5));
        System.out.println(Arrays.toString(fibonacciIteration(10)));
    }

    static int[] fibonacciIteration(int n) {
        int[] result = new int[n];
        if (n > 0)
            result[0] = 0;

        if (n > 1)
            result[1] = 1;

        for (int i = 2; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result;
    }

    static int factorialIteration(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    static int factorialRecursion(int n) {
        if (n == 1)
            return n;
        return n * factorialRecursion(n - 1);
    }

    static int sumNaturalIteration(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    static int sumNaturalRecursive(int n) {
        if (n == 0)
            return n;
        return n + sumNaturalRecursive(n - 1);
    }

}
