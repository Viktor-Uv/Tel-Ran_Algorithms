package lesson34;

import java.util.concurrent.*;

public class A_FutureTester {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<String> result = service.submit(new WorkCallable());
        // result.isDone() // готов ли результат
        System.out.println(result.get()); // поток, в котором выполняется Future.get(),
        //                                  заблокируется на время выполнения Callable

        service.shutdown();
    }

    static class WorkCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(800);
            return "Time is: " + System.currentTimeMillis();
        }
    }

}
