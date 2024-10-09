package lesson28.exceptions;

import java.io.IOException;

public class ExceptionTester {
    public static void main(String[] args) {
        solution(0);
        solution(2);
        System.out.println("hello, world!");
    }

    public static void solution(int n) {
        // запустите,
        // добавьте обработчик исключений,
        // перехватывайте и распечатывайте все полученные исключения
        try {
            int x = 10 / n;
            int[] array = new int[n];
            array[x] = 10;
        } catch (ArithmeticException e) {
            System.err.println("ArithmeticException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

    }
}
