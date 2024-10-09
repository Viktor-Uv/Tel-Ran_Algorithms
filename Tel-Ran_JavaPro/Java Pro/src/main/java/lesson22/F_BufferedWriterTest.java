package lesson22;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class F_BufferedWriterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите несколько строк разделяя их вводом ");
        System.out.println("stop закончит ввод");
        try (
                Writer writer = new FileWriter("./src/main/java/lesson22/files/test-output.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                ) {
            String line = scanner.nextLine();
            while (!line.equals("stop")) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line = scanner.nextLine();
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
