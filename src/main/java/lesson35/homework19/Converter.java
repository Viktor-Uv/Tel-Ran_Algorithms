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
        CompletableFuture<Double> converter =
                CompletableFuture
                        .supplyAsync(
                                () -> convert(10, "EUR", "GBP") // 8.7213
                        )
                        .handle(
                                (amount, exception) -> {
                                    if (exception != null) {
                                        System.err.println("Exception: " + exception.getMessage());
                                        return null;
                                    } else {
                                        return convert(amount, "GBP", "USD"); // 10.591
                                    }
                                }
                        );

        try {
            System.out.println(converter.get()); // 10.591
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
            throw new RuntimeException(e);
        }
    }
}
