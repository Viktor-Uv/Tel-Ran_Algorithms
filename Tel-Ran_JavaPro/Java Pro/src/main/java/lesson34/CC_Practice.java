package lesson34;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class CC_Practice {
    public static void main(String[] args) {
        // напишите CompletableFuture из нескольких стадий
        // 1 стадия - создать и вернуть рандомное число от 0 до 200
        //       и потом его распечатать

        CompletableFuture <Integer> cfi = CompletableFuture.supplyAsync(
                new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        return new Random().nextInt(201);
                    }
                }
        )
                // 2 стадия - умножьте полученное число само на себя
//                .thenApply() // in the same Thread
                .thenApplyAsync( // in a new Thread
//                        i -> i * i
                        new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(Integer i) {
                                System.out.println("Before multiplication: " + i);
                                return i * i;
                            }
                        }
                );

        // CompletableFuture запустится только после того,
        // как будет вызван его get()
        try {
            System.out.println(cfi.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
