package lesson34;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

public class D_CompletableFutureCombine {
    public static void main(String[] args) {

        // thenCombine - механизм, позволяющий объединить между собой
        // результаты двух CompletableFuture

        CompletableFuture<String> result = CompletableFuture.supplyAsync(
                () -> hello()
        )
                .thenCombine(
                        CompletableFuture.supplyAsync(() -> world()),
                        // (s1, s2) -> s1 + " " + s2
                        new BiFunction<String, String, String>() {
                            @Override
                            public String apply(String s1, String s2) {
                                return s1 + " " + s2;
                            }
                        }
                );

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    public static String hello() {return "Hello";}
    public static String world() {return "World";}
}
