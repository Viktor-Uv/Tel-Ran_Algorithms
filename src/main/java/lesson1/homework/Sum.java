package lesson1.homework;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        System.out.println("Enter 3 numbers:");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        scanner.close();

        int sum = x + y + z;

        System.out.println("Sum is: " + sum);
    }
}
