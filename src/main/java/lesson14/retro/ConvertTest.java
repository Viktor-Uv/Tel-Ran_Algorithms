package lesson14.retro;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

// https://api.frankfurter.app/latest?amount=10&from=GBP&to=USD

public class ConvertTest {
    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.frankfurter.app") // site
                .addConverterFactory(GsonConverterFactory.create()) // json -> Rate
                .build();

        // попросим ретрофит создать нам прокси-класс по аннотированному интерфейсу
        FrankfurterService service = retrofit.create(FrankfurterService.class);

        Call<Rate> call = service.convert(100, "EUR", "USD");
        Response<Rate> response = call.execute();
        Rate rate = response.body();
        System.out.println(rate.rates);

        System.exit(0);
    }
}
