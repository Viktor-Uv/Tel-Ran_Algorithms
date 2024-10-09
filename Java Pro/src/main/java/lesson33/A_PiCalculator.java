package lesson33; // Date 11.10.2023

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class A_PiCalculator {

    // количество точек внутри четверти окружности
    private static AtomicInteger inside = new AtomicInteger();

    // количество точек всего
    private static AtomicInteger all = new AtomicInteger();

    // для инициализации счетчиков
    public static void init() {
        inside = new AtomicInteger(0);
        all = new AtomicInteger(0);
    }

    public static void main(String[] args) {
        runner(10); // \/
        runner(10); // прогрузка в памяти
        runner(10); // /\


        runner(1);
        runner(2);
        runner(5);
        runner(10);
        runner(20);
        runner(40);
        runner(100);
        // сколько всего ядер на конкретной машине
        System.out.println(Runtime.getRuntime().availableProcessors());
        // Result - it's not necessary to use many Threads. 2-4 is enough

    } // Main

    static void runner(int numberOfThreads) {
        long before = System.currentTimeMillis();

        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        // напишите код, который запустит PiRunnable на service 1_000_000 раз
//        for (int i = 0; i < 10_000_000; i++) {
//            service.submit(new PiRunnable()); // not optimal: creates 10M new objects
//        }
        PiRunnable piRunnable = new PiRunnable();
        for (int i = 0; i < 10_000_000; i++) {
            service.submit(piRunnable);
        }

        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long after = System.currentTimeMillis();
        System.out.printf(
                "threads: %d, t: %d, i: %d, a: %d, pi: %f\n",
                numberOfThreads,
                (after - before),
                inside.get(),
                all.get(),
                4.0 * inside.get() / all.get()
        );
    } // runner(int)

    private static Random r = new Random();
    // рандомно генерировать x и y для точки
    // вычислять внутри или вне окружности
    // добавляет это в inside и в all
    static class PiRunnable implements Runnable {
        @Override
        public void run() {
            // добавьте 1 в all
            all.incrementAndGet();
            // сгенерируйте используя Random
            double x = r.nextDouble();
            double y = r.nextDouble();
            // вычислите расстояние до центра окружности по теореме Пифагора
            double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            // если это расстояние меньше или равно 1 добавьте 1 в inside
            if (r <= 1) {
                inside.incrementAndGet();
            }
        }
    }
}
