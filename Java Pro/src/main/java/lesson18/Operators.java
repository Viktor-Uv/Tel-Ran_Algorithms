package lesson18;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public class Operators {
    public static void main(String[] args) {
        // Operator
        // это Function у которой одинаковы входной и возвращаемый тип
        // IntUnaryOperator - оператор принимающий и возвращающий int

        int [] numbers = new int[] {1, 3, 5, 2, 4, 11};
        int sum = Arrays.stream(numbers) // превратит массив в поток
                            .reduce(0, new IntBinaryOperator() {
                                @Override
                                public int applyAsInt(int left, int right) {
                                    return left + right;
                                }
                            });
        System.out.println(sum);
    }
}
