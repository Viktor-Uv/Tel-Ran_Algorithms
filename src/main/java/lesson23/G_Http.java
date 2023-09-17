package lesson23;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class G_Http {
    public static void main(String[] args) {
        /*
        https://api.frankfurter.app/latest?amount=10&from=GBP&to=USD
        {"amount":10.0,"base":"GBP","date":"2023-09-11","rates":{"USD":12.5207}}
     */
        String service = "https://api.frankfurter.app/latest?amount=10&from=GBP&to=USD";
        StringBuilder stringBuilder = new StringBuilder(); // Simplifies String concatenation
        try {
            // the resources inside this block don't implement AutoCloseable:
            URL url = new URL(service); // creates a URL object
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection(); // opens connection
            // to URL, and converts result to the known connection type (in this case - https)

            // try-with-resources block, which ensures that the resources declared
            // within the parentheses are closed automatically after the block is executed
            try (
                    InputStream is = connection.getInputStream(); // read the bytes sent by the server
                    Reader reader = new InputStreamReader(is); // read the bytes as characters
                    BufferedReader bufferedReader = new BufferedReader(reader); // read the characters as lines
            ) {
                bufferedReader.lines() // convert all lines read to a single line
                        .forEach(line -> stringBuilder.append(line));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Gson gson = new Gson();
        // Create class instance of Rate using read data in json format
        Rate currencyRate = gson.fromJson(stringBuilder.toString(), Rate.class);
        System.out.println(currencyRate.rates);
    }
}
