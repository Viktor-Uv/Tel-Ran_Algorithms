package lesson5;

public class MultiDimensionalArrayTester {
    public static void main(String[] args) {
        int [] a = {1, 2, 3}; // 1-dimensional

        int [] [] c = new int[][] { // multi-dimension
                {1, 3, 5},          // 0
                {4},                // 1
                {-10, 20, -40, 5}   // 2
        };

        System.out.println(c[2][2]); // -40
        System.out.println(c[2][3]); // -5

        System.out.println(c.length); // 3
        System.out.println(c[2].length); // 4

        System.out.println(
                sum(c)      // -12
        );

    }

    public static int sum(int [] [] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                sum += a[i][j];
            }
        }
        return sum;
    }


}
