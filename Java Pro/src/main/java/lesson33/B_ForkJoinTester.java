package lesson33;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class B_ForkJoinTester {
    public static void main(String[] args) {

        // можно воспользоваться встроенным в ява пулом потоков
        // недостатки/достоинства:
        //      не нужно ждать
        //      не нужно настраивать
        ExecutorService service = ForkJoinPool.commonPool();

        service.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                            System.out.println("Time is: " + System.currentTimeMillis()
                            + " thread " + Thread.currentThread().getId()
                            );
                        } catch (InterruptedException e) {
                            //
                        }
                    }
                }
        );

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
