package lesson34;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class G_Exceptions {
    public static void main(String[] args) {
//        CompletableFuture<String> cfs = CompletableFuture.supplyAsync(() -> "234");
        CompletableFuture<String> cfs = CompletableFuture.supplyAsync(() -> "hello");

        CompletableFuture<String> result = cfs
                .thenApply(s -> Integer.parseInt(s))
                .thenApply(i -> i * i)
                .thenApply(i -> "Result is: " + i)
                .exceptionally( // catch Exceptions
                        t -> "Problem: " + t.getMessage()
                );

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
