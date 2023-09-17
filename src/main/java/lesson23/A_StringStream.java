package lesson23;

/*
https://www.oreilly.com/api/v2/epubs/9781449372477/files/httpatomoreillycomsourceoreillyimages1707640.png
 */

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

public class A_StringStream {
    public static void main(String[] args) {
        String hello = "hello\nworld\nhow are you?";

        // считайте и распечатайте строки из этой строки

        // Method 1
        /*
        hello.lines()
                .flatMap(line -> Arrays.stream(line.split("\n")))
                .forEach(System.out::println);
         */

        // Method 2 (BufferedReader will already read it line-by-line)

        Reader stringReader = new StringReader(hello);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        bufferedReader.lines()
                .forEach(System.out::println);
    }
}
