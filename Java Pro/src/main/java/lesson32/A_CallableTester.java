package lesson32; // Date 09.10.2023

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class A_CallableTester {
    public static void main(String[] args) {
        // Runnable не возвращает результат
        new Thread(new MyRunnable()).start();

        Callable<String> callable = new MyCallable();

        // Future - будущий результат
        FutureTask<String> task = new FutureTask<>(callable);
        new Thread(task).start();


        // конструктор Thread принимает на вход Runnable а не Callable
        // new Thread(callable).start();

        try {
            // если результат не готов, то get блокирует
            // поток, в котором вызывается (в данном случае 0 поток)
            // до готовности результата
            System.out.println(task.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Hello " + System.currentTimeMillis();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello " + System.currentTimeMillis());
    }
}
