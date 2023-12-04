package debug;

import java.util.Random;

public class DebugExample {
    public static void main(String[] args) {
        System.out.println(
                getResult(20)
        );
    }

    public static String epoch = "Aquarius";

    public static String getResult(int id) {
        // Comment can't be a break point

        String name = "max";
        long value = getRandom(name);
        for (int i = 0; i < 10; i++) {
            String a = "hello" + i * i * id;
        }
        return "" + System.currentTimeMillis();
    }

    public static long getRandom(String name) {
        Random random = new Random();
        return random.nextLong();
    }

}
