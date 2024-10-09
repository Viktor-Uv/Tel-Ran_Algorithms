package lesson23;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;

public class F_Gson {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        Dog d1 = new Dog("Polkan", 5, true);
        write(
                d1,
                "./src/main/java/lesson23/files/dog.json.txt"
        );

        Dog d2 = read("./src/main/java/lesson23/files/dog.json.txt");
        System.out.println(d2);
    }

    public static void write(Object o, String filename) {
        try (
                FileWriter fileWriter = new FileWriter(filename)
        ) {
            gson.toJson(o, fileWriter);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Dog read(String filename) {
        Dog dog = null;

        try (
                FileReader fileReader = new FileReader(filename);
        ) {
            dog = gson.fromJson(fileReader, Dog.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return dog;
    }
}
