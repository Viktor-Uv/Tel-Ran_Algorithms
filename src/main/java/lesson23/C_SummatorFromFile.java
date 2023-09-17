package lesson23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class C_SummatorFromFile {
    public static void main(String[] args) {
        // посчитайте сумму цифр из файла digits.txt
        try (
                Reader fileReader = new FileReader(
                        "./src/main/java/lesson23/files/digits.txt"
                );
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            System.out.println(
                    bufferedReader.lines()
                            .mapToInt(Integer::parseInt) // -> int, (just .map() would give Integer)
                            .sum() // .sum() есть только в int (!Integer)
            );
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
