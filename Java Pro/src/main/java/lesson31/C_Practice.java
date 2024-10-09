package lesson31;

public class C_Practice {
    public static void main(String[] args) {
        // создайте и запустите поток,
        // который выполнит function1 и распечатает результат
        new Thread(() -> System.out.println(function1()))
                .start();

        Thread t2 = new MyThread("hello");
        t2.start();

        // создайте Thread который в run выполняет function1
        // и потом запускает MyThread передав в качестве параметра
        // результат function1
        Thread t3 = new Thread() {
            @Override
            public void run() {
                new MyThread(function1())
                        .start();
            }
        };
        t3.start();

    } // Main

    // напишите статический класс который расширяет Thread и в конструкторе
    // принимает на вход строку
    // принимаемую строку использует в run, для того чтобы вызвать с ней function2
    // результат function2 нужно распечатать
    public static class MyThread extends Thread {
        private final String s;

        public MyThread(String s) {
            this.s = s;
        }

        @Override
        public void run() {
            System.out.println(function2(s));
        }
    }

    public static String function1() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "" + Thread.currentThread().getId() + ":" + System.currentTimeMillis();
    }

    public static String function2(String text) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return text + "|" + Thread.currentThread().getId() + ":" + System.currentTimeMillis();
    }
}
