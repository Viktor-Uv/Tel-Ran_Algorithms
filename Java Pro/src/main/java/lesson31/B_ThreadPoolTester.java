package lesson31;

import java.util.Random;
import java.util.concurrent.*;

/*
    пул потоков - набор потоков, thread pool
        набор из потоков выполнения + очередь задач для выполнения
        задания в очереди это либо Runnable, либо Callable
 */

/*
 * ExecutorService: The ExecutorService interface provides methods for managing and executing tasks
 *      in a pool of threads. You can use one of the factory methods in the Executors class to create
 *      an ExecutorService instance, such as: ExecutorService executor = Executors.newFixedThreadPool(4);
 *      This creates a thread pool with four threads that can execute four tasks at a time.
 *
 * Executors: The Executors class provides factory methods for creating different kinds of
 *      ExecutorService instances, such as fixed thread pool, cached thread pool, single thread
 *      executor, scheduled thread pool, etc. These methods simplify the creation and management
 *      of thread pools.
 *
 * Job: A job is a unit of work that can be executed by a thread or an executor service.
 *      A job can be represented by a Runnable or a Callable object.
 *
 * Callable: The Callable interface is similar to the Runnable interface, except that it allows
 *      the task to return a value and throw an exception. The Callable object can be submitted
 *      to an ExecutorService using the submit() method, which returns a Future object that represents
 *      the result of the task.
 *
 * Future: A Future object represents the result of an asynchronous computation. It provides methods
 *      to check if the computation is complete, cancel it, or get its result. You can use the
 *      get() method to wait for the result of the task, or use the isDone() method to check
 *      if it is done without blocking.
 */

public class B_ThreadPoolTester {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2); // 2 потока выполнения в пуле
        Runnable r = new Work();
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);
        service.submit(r);

        // Создаем экземпляр Callable
        Job callable = new Job();

        // Future - будущий результат
        Future<String> result = service.submit(callable);

        // Future<>.get() блокирует поток в котором выполняется
//        try {
//            System.out.println(result.get());
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//        }
        if (result.isDone()) {
            // можем результат запросить, он готов
            System.out.println("Future result is ready");
        } else {
            System.out.println("Future result is not ready, can do something else");
        }

        service.shutdown(); // дожидается выполнения всех задач в очереди
        // service.shutdownNow(); // не дожидается выполнения всех заданий в очереди
    }

    public static class Work implements Runnable {
        @Override
        public void run() { // Runnable ничего не возвращает
            Random r = new Random();
            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread with id: " + Thread.currentThread().getId());
        }
    }

    // Callable - задача, которая возвращает результат
    public static class Job implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(500);
            return "" + System.currentTimeMillis();
        }
    }
}
