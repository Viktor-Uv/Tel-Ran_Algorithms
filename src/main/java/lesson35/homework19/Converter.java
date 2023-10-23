package lesson35.homework19;

import lesson14.retro.FrankfurterService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * JavaPro. Homework #19
 *
 * @author Viktor Uvarchev
 * @version 20 Oct 2023
 */

/*
 * Task:
 * Воспользуйтесь функцией convert
 * И вызовите последовательно перевод 10 евро ("EUR") в фунты ("GBP") и потом для полученной суммы
 * вызовите перевод из фунтов в доллары ("USD") воспользовавшись CompletableFuture.
 * Распечатайте результат.
 */

public class Converter {
    public static void main(String[] args) {
        CompletableFuture<String> converter =
                CompletableFuture
                        // суффикс Async значит что этот CF будет выполняться
                        // на пуле потоков
                        // если не указать то это будет ForkJoin
                        .supplyAsync(
                                () -> convert(10, "EUR", "GBP") // 8.7213
                        )
                        // все методы без Async запускаются в потоке в котором
                        // выполняется .get()
                        .thenApply(
                                result -> convert(result, "GBP", "USD")
                        )
                        .handle( // .handle is only to return result, not to start another work!
                                (result, error) -> {
                                    if (error != null) {
                                        return error.getMessage();
                                    } else {
                                        return "" + result; // 10.591
                                    }
                                }
                        );
        // Comment to CORRECTION 23.10.2023:
        // не самый хороший вариант в handle делать еще один асинхронный
        // вызов - все же handle предназначен для обработки ошибок
        // Такой вариант не позволит обработать ошибку во втором сетевом вызове
        // + changed converter to return String

        // цепочка запускается только после вызова get
        try {
            converter.get(); // 10.591
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);

    } // Main

    public static double convert(double amount, String from, String to) {
        FrankfurterService service = new Retrofit.Builder()
                .baseUrl("https://api.frankfurter.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FrankfurterService.class);
        try {
            return service.convert(amount, from, to).execute().body().rates.get(to);
        } catch (Exception e) {
            // если функция выбрасывает CheckedException, то имеет смысл перехватить его
            // и выбросить заново как UnCheckedException
            throw new RuntimeException(e);
        }
    }
}
