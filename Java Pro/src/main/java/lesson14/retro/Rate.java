package lesson14.retro;

import java.util.HashMap;
import java.util.Map;

// https://api.frankfurter.app/latest?amount=10&from=GBP&to=USD
/*
 * https://api.frankfurter.app
 *      /latest?
 *              amount=10&
 *              from=GBP&
 *              to=USD
 */

// {"amount":10.0,"base":"GBP","date":"2023-08-09","rates":{"USD":12.7269}}
public class Rate {
    public double amount;
    public String base;
    public String date;
    public Map<String, Double> rates = new HashMap<>();

    @Override
    public String toString() {
        return "Rate{" +
                "amount=" + amount +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
