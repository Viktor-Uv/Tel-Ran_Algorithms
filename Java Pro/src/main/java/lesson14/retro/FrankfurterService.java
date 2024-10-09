package lesson14.retro;

/*
 * https://api.frankfurter.app
 *      /latest?
 *              amount=10&
 *              from=GBP&
 *              to=USD
 */

// https://square.github.io/retrofit/

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FrankfurterService {
    @GET("/latest") // в какой веб-метод на сервере будет уходить запрос
    Call<Rate> convert(
            @Query("amount") double amount,
            @Query("from")String from,
            @Query("to")String to
    );
}
