package lesson32;

import java.util.Random;
import java.util.concurrent.*;

/**
 * JavaPro. Homework #16
 *
 * @author Viktor Uvarchev
 * @version 10 Oct 2023
 */

/*
 * Task 1.
 * Переделайте предыдущее задание про потоки выполнения (класс lesson31.hw.HWThreads) на Callable.
 * Создайте Callable, из метода call которой возвращайте результат выполнения функции waitSomeTime.
 * В main создайте и запустите два потока, выполняющие этот Callable и распечатайте сумму результатов.
 * В классе CallableTester можно посмотреть пример запуска Callable на потоке выполнения и получения
 * из Callable результата.
 */

public class Homework16 {
    public static void main(String[] args) {

        Callable<Integer> callable = () -> toWait(); // call() {return toWait();}

        ExecutorService service = Executors.newFixedThreadPool(2); // create 2 threads

        FutureTask<Integer> task1 = new FutureTask<>(callable);
        FutureTask<Integer> task2 = new FutureTask<>(callable);

        service.submit(task1); // start threads
        service.submit(task2); // start threads

        try {
            System.out.println("Total wait time: " + (task1.get() + task2.get() + " ms")); // print result
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        service.shutdown(); // close threads

    } // Main

    public static int toWait() {
        int waitTime = new Random().nextInt(0, 1001);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return waitTime;
    }
}
