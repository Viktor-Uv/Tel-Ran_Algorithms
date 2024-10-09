package lesson22; // Date 06.09.2023

import java.util.Scanner;

public class B_ScannerTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число и нажмите ввод: ");
        int n = scanner.nextInt();
        System.out.print("Введите целое число и нажмите ввод: ");
        int k = scanner.nextInt();
        System.out.println("результат сложения: " + (n + k));


        System.out.print("Введите double и нажмите ввод: ");
        double d = scanner.nextDouble();
        System.out.println("число " + d);

    }
}
