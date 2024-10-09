package lesson28.exceptions;

public class DivisionByZeroTester {
    public static void main(String[] args) {
        try {
            System.out.println(divide(10, 2));
            System.out.println(divide(10, 0));
        } catch (DivisionByZeroException e) {
            System.err.println("DivisionByZeroException: " + e.getMessage());
        }
    }

    public static int divide(int numerator, int denominator) throws DivisionByZeroException {
        if (denominator == 0) {
            throw new DivisionByZeroException("Division by zero");
        } else {
            return numerator / denominator;
        }
    }
}
