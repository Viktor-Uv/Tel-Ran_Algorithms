package lesson34;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class F_Parallel {
    public static void main(String[] args) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Good bye, ");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "cruel ");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "world!");

        // параллельное выполнение
        CompletableFuture<Void> combined =
                CompletableFuture.allOf(f1, f2, f3);

        // нужно выполнить get чтобы вычисление запустилось
        try {
            combined.get();

            // результаты f1 f2 f3 готовы
            System.out.println(f1.get() + f2.get() + f3.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
}
