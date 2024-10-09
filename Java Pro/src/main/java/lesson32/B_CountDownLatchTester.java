package lesson32;

/*
    CountDownLatch - способ альтернативный для join
    в нем есть счетчик который можно уменьшать
    можно блочиться до того момента пока счетчик не станет равен 0
 */

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class B_CountDownLatchTester {
    public static void main(String[] args) {
        System.out.println("Starting ...");

        CountDownLatch latch = new CountDownLatch(3); // начальное значение счетчика

        // запускаем потоки,
        // которые уменьшают счетчик в процессе работы
        IntStream.range(0, 3).forEach(
                i -> new Thread(new Processor(latch)).start()
        );

        try {
            latch.await(); // блокируемся пока счетчик не станет равным 0
        } catch (Exception e) { /* */ }
        // в этом месте оказываемся, когда счетчик == 0

        System.out.println("Completed ...");
    }
}

class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Latch value at start is: " + latch.getCount());
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        latch.countDown();
        System.out.println("Latch value at finish is: " + latch.getCount());
    }
}
