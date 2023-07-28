package lesson3.homework3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Algorithms. Homework #3
 *
 * @author Viktor Uvarchev
 * @version 27 Jul 2023
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

        // Task 2* - recursive & interactive
        startTowerOfHanoi(3, "solution");

        // Task 2* - interactive
        startTowerOfHanoi(5, "play");
    }

    // Function for Task 1
    private static int[] fibonacciRecursion(int n) {
        if (n == 1) { // Handle base case 1
            return new int[] {0};
        } else if (n == 2) { // Handle base case 2
            return new int[] {0, 1};
        } else {
            int[] result = fibonacciRecursion(n - 1); // Solve size n-1
            result = Arrays.copyOf(result, n); // Extend array
            result[n - 1] = result[n - 2] + result[n - 3]; // Solve sum of 2 previous elements
            return result;
        }
    }

    // Functions for Task 2*
    private static void startTowerOfHanoi(int noOfDisks, String command) {
        int[][] towers = new int[3][noOfDisks];
        for (int i = 0; i < noOfDisks; i++) {
            towers[0][i] = i + 1;
        }
        int[] stepCounter = {0};
        if (command.equals("solution")) {
            System.out.println("\nSolution for Tower of Hanoi with " +
                    noOfDisks + " disks:");
            printTowers(towers);
            towerOfHanoi(noOfDisks, 'A', 'B', 'C', towers, stepCounter);
            System.out.println("End of the solution. Steps taken: " + stepCounter[0]);
        } else if (command.equals("play")) {
            System.out.println("\n\nTry to solve Tower of Hanoi with " +
                    noOfDisks + " disks yourself!:");
            printTowers(towers);
            System.out.println("Enter your moves at the prompts followed by Enter." +
                    "\nYou can use A, B, C and 1, 2, 3");

            while (towers[2][0] != 1) { // Check if third tower is completed
                char from, to;
                int diskSize = -1;
                do {
                    from = validateInput("\nTake disk From: ");
                    to = validateInput("Place disk On: ");
                    diskSize = moveDisks(from, to, towers);
                } while (diskSize < 0);
                System.out.println("Moving disk size " + diskSize +
                        " from " + from + " to " + to +
                        " --> see result below:");
                printTowers(towers);
                stepCounter[0]++;
            }
            System.out.println("You have won the game! Steps taken: " + stepCounter[0] +
                    " (minimum possible: " + ((int) Math.pow(2, noOfDisks) - 1) + ")");

        } else {
            System.out.println("Wrong command (accepted commands: 'solution' and 'play')");
        }
    }

    // Recursive solution
    private static void towerOfHanoi(int n, char source, char temp, char dest, int[][] towers, int[] counter) {
        if (n > 0) { // Base case
            // Shift (n-1) disks to the temporary peg
            towerOfHanoi(n - 1, source, dest, temp, towers, counter);

            // Shift n-th disk from source peg to destination peg
            int toIndex = findFirstNonZero(towers[dest - 'A']) - 1;
            int fromIndex = findFirstNonZero(towers[source - 'A']);
            towers[dest - 'A'][toIndex] = towers[source - 'A'][fromIndex];
            towers[source - 'A'][fromIndex] = 0;

            System.out.println("\nMoving disk size " + n +
                    " from " + source + " to " + dest +
                    " --> see result below:");
            printTowers(towers);
            counter[0]++;


            // Shift remaining (n-1) disks from temporary peg to destination peg
            towerOfHanoi(n - 1, temp, source, dest, towers, counter);
        }
    }

    // Visualise towers
    private static void printTowers(int[][] towers) {
        for (int i = 0; i < towers[0].length; i++) {
            for (int j = 0; j < towers.length; j++) {
                // Print spaces before disks
                for (int k = 0; k < towers[0].length - towers[j][i] + 1; k++)
                    System.out.print("_");

                // Print disks
                for (int l = 0; l < towers[j][i]; l++)
                    System.out.print("##");

                // Print spaces after disks
                for (int k = 0; k < towers[0].length - towers[j][i] + 1; k++)
                    System.out.print("_");

                // Print separator between pegs
                System.out.print(" ");
            }
            // New line after each layer
            System.out.println();
        }
    }

    // Find index of the first element that is not zero
    private static int findFirstNonZero(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                return i;
            }
        }
        // If all elements are zero, return array length
        return array.length;
    }

    // Validate user inputs
    private static char validateInput(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();

        return switch (choice) {
            case "1", "a", "A" -> 'A';
            case "2", "b", "B" -> 'B';
            case "3", "c", "C" -> 'C';
            default -> {
                System.out.println("Wrong input, use A, B, C and 1, 2, 3");
                yield validateInput(message);
            }
        };
    }

    private static int moveDisks(char source, char dest, int[][] towers) {
        int toIndex = findFirstNonZero(towers[dest - 'A']) - 1;
        int fromIndex = findFirstNonZero(towers[source - 'A']);

        if (source == dest) {
            return towers[dest - 'A'][toIndex + 1];
        }

        boolean fromEmpty = fromIndex >= towers[source - 'A'].length;
        if (fromEmpty) {
            System.out.println("Error (tower " + source + " is empty)");
            return -1;
        }

        int bottom = towers[0].length - 1;
        boolean notToBottom = towers[dest - 'A'][bottom] > 0;
        if (notToBottom) {
            boolean NotToLarger = towers[source - 'A'][fromIndex] > towers[dest - 'A'][toIndex + 1];
            if (NotToLarger) {
                System.out.println("Error (tower " + dest + " has a larger disk already)");
                return -1;
            }
        }

        towers[dest - 'A'][toIndex] = towers[source - 'A'][fromIndex];
        towers[source - 'A'][fromIndex] = 0;
        return towers[dest - 'A'][toIndex];
    }
}
