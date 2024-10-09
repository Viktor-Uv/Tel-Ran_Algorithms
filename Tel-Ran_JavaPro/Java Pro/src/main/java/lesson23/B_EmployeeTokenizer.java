package lesson23;

import lesson19.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.StringTokenizer;

public class B_EmployeeTokenizer {
    public static void main(String[] args) {
        write();
        read();
    }

    /*
    https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html
    https://docs.oracle.com/javase/8/docs/api/java/util/StringTokenizer.html
     */

    public static void write() {
        Employee max = new Employee("Max Lotkov", 27, "analyst");
        try (
                // PrintWriter Prints formatted representations of objects to a text-output stream
                PrintWriter printWriter = new PrintWriter(
                        "./src/main/java/lesson23/files/employee.txt"
                );
        ) {
            printWriter.format("%s|%d|%s", max.getName(), max.getAge(), max.getPosition());
            // https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void read() {
        try (
                Reader fileReader = new FileReader(
                        "./src/main/java/lesson23/files/employee.txt"
                );
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String s = bufferedReader.readLine();
            // The string tokenizer class allows an application to break a string into tokens
            StringTokenizer tokenizer = new StringTokenizer(s, "|");
            // Max Lotkov|27|analyst
            String name = tokenizer.nextToken();
            int age = Integer.parseInt(tokenizer.nextToken());
            String position = tokenizer.nextToken();
            System.out.println(new Employee(name, age, position));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
