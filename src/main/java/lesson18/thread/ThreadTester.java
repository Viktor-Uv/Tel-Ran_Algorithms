package lesson18.thread;

public class ThreadTester {
    public static void main(String[] args) {
        // Runnable это работа которую мы хотим запустить параллельно main
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from new thread!");
            }
        };

        // Thread - новый поток
        Thread t = new Thread(r);
        t.start(); // запуск нового потока

    }
}
