package lesson34;

// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class C_CompletableFutureCreation {
    public static void main(String[] args) {
        // runAsync запускает на встроенном ExecutorService - ForkJoinPool
        // runAsync не может возвратить значение
        // аналог создания и запуска Thread-а через Runnable
        CompletableFuture<Void> future = CompletableFuture.runAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {}
                        System.out.println("Current thread is: " + Thread.currentThread().getId());
                    }
                }
        );

        // CompletableFuture не запускается пока не вызван его метод get
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // supplyAsync принимает на вход Supplier<T>
        // supplyAsync выполняется в порожденном потоке
        // предназначен для возвращения значения
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(
                new Supplier<String>() {
                    @Override
                    public String get() {
                        return "" + System.currentTimeMillis();
                    }
                }
        )
                // thenApplyAsync принимает на вход Function<T,R>
                // и может возвращать значение
                .thenApplyAsync(
                        new Function<String, String>() {
                            @Override
                            public String apply(String s) {
                                return "Hello, " + s;
                            }
                        }
                )
                // thenAccept если результат не нужен, а важны сами действия
                .thenAccept(
                        new Consumer<String>() {
                            @Override
                            public void accept(String s) {
                                System.out.println(s.length());
                            }
                        }
                );

//        try {
//            System.out.println(cf.get()); // если возвращает значение
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        try {
            cf.get(); // если результат не нужен
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
