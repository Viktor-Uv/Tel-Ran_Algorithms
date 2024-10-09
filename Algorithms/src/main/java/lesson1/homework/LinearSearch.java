package lesson1.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LinearSearch {
    public static void main(String[] args) {
        int[] randomArray = new int[10];
        Random rand = new Random();
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rand.nextInt(1, 10);
        }

        System.out.print("Guess the integer [1-9]: ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        scanner.close();

        boolean isFound = false;
        for (int i = 0; i < randomArray.length; i++) {
            if (randomArray[i] == x) {
                System.out.println("Found in index: " + i);
                isFound = true;
            }
        }

        if (!isFound)
            System.out.println("Not found");

        System.out.println(
                "These were the numbers: " +
                Arrays.toString(randomArray)
        );
    }
}
