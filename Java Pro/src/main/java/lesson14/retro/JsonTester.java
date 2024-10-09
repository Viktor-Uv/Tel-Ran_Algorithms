package lesson14.retro;

import com.google.gson.Gson;
import lesson14.Student;

/*
 * https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit/2.9.0 // retrofit library
 * https://github.com/public-apis/public-apis // A collective list of free APIs for use in software and web development
 * https://www.frankfurter.app/docs/ // API tracks foreign exchange references rates published by the European Central Bank
 *
 * https://square.github.io/retrofit/ // good retrofit materials
 */

public class JsonTester {
    public static void main(String[] args) {
        Gson gson = new Gson(); // гугловая библиотека для работы c JSON
        Student max = new Student("Max Kotkov", 3, "B-123");
        String json = gson.toJson(max);
        System.out.println(json);

        String zina = "{\"name\":\"Zina Pavlova\",\"year\":2,\"group\":\"B-55\"}";
        Student ina = gson.fromJson(zina, Student.class);
        System.out.println("name: " + ina.getName() + ", year: " + ina.getYear());
        // https://api.frankfurter.app/latest?amount=10&from=GBP&to=USD --> {"amount":10.0,"base":"GBP","date":"2023-08-09","rates":{"USD":12.7269}}

    }
}
