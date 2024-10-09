package lesson24;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class D_Cars {
    public static void main(String[] args) {
        // Gson is a Java library that can be used to convert Java objects to JSON and back.
        Gson gson = new Gson(); // create a Gson object to parse JSON data
        try ( // create a reader object to read from the file cars.txt
              Reader fileReader = new FileReader("./src/main/java/lesson24/files/cars.txt")
        ) {
            /* Type: Returns a string describing this type, including information about any type parameters.
            *
            * TypeToken is a class that represents a generic type in Java. It is useful when we want to
            * work with generic types that are erased at runtime due to type erasure. For example,
            * if we want to use Gson to convert a JSON string to a list of Car objects, we need
            * to tell Gson what type of list we want. */
            // Create a TypeToken object with ArrayList<Car> as the type parameter
            // by using an anonymous subclass.
            Type listType = new TypeToken<ArrayList<Car>>() {}.getType(); // get a Type object that
                                                                          // represents ArrayList<Car>
            List<Car> cars = gson.fromJson(fileReader, listType); // Pass the Type object and the file reader
            // object to Gson’s 'fromJson' method to deserialize the JSON data into a list of Car objects

            // посчитайте стоимость всех машин Peugeot
            System.out.println(
                    cars.stream()
                            .filter(car -> car.getMaker().equals("Peugeot"))
                            .mapToDouble(Car::getPrice) // .mapToDouble() has .sum(), .map() - doesn't
                            .sum()
            );

            // найдите самую дорогую машину из тех что стоят меньше 30000
            cars.stream()
                    .filter(c -> c.getPrice() < 30_000)
                    .max(Comparator.comparingDouble(Car::getPrice))
                    .ifPresent(System.out::println);

            System.out.println(cars);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
