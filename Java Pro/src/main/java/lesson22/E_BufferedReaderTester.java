package lesson22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class E_BufferedReaderTester {
    public static void main(String[] args) {
        try (
                Reader reader = new FileReader("./src/main/java/lesson22/files/test.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                ) {
            // reader.read() читает либо символы либо массивы символов

            // BufferedReader улучшает reader, позволяя считывать построчно
            String line = bufferedReader.readLine();
            while (line != null) { // больше нет строк в файле
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }
}
