package lesson28.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/*
Throwable - базовый класс для всех ошибок и исключений
    Error - ошибки от которых нет возможности восстановиться
        OutOfMemory ...
    Exception
        "Checked exception" - мы обязаны их проверять -
        редкие, но вполне нормальные ситуации при работе программы,
        дают возможность как-то выйти из особой ситуации


        RuntimeException - "ошибки программиста" - ситуации которых можно было избежать
            либо внимательностью, либо дополнительной проверкой
            можно перехватывать, но чаще всего нет смысла
            NullPointerException, ArithmeticException, IndexOutOfBoundsException
 */
public class TryWithResourceTester {
    public static void main(String[] args) {
        String result = getRandomString();
        if (result != null) {
            System.out.println(
                    result.length()
            );
        }

        // Bad solution - file could be left open:
        /*
        try {
            FileInputStream fis = new FileInputStream("./src/main/java/lesson28/files/digits.txt");
            // work with file... -> если исключение случится здесь, то файл не будет закрыт
            fis.close(); // закрыть файл если он больше не нужен
        }
//        catch (FileNotFoundException e) {
//            System.err.println("File not found");
//        }
        catch (NullPointerException | IOException e) { // тоже перехватит FileNotFoundException
            System.err.println("IO Exception");
        }
         */


        // Not the best solution - it's really bulky:
        /*
        FileInputStream fis = null; // must be declared outside the try block
        try {
            fis = new FileInputStream("./src/main/java/lesson28/files/digits.txt");
            // work with file...
        }
//        catch (FileNotFoundException e) {
//            System.err.println("File not found");
//        }
        catch (NullPointerException | IOException e) { // тоже перехватит FileNotFoundException
            System.err.println("IO Exception");
        }
        finally { // выполняется всегда, независимо вышло исключение или нет
                try {
                    // .close() тоже может вызывать исключения
                    fis.close(); // закрыть файл если он больше не нужен
                } catch (IOException e) {
                    System.err.println("IO Exception");
                }
        }
         */

        // Better solution: use try-with-resource
        // автоматически закрывает AutoClosable после окончания try-catch блока
        try (
                // те объекты, которые являются AutoClosable, открываем с помощью try-with-resource
                // для них блок finally не нужен
                FileInputStream fis = new FileInputStream("./src/main/java/lesson28/files/void.txt");
        ) {
            // с объектами, которые не являются AutoClosable, работаем в try блоке
            // их закрываем в блоке finally
            // work with file

        } catch (NullPointerException | IOException e) { // тоже перехватит FileNotFoundException
            System.err.println("IO Exception: " + e.getMessage());
        }

    }

    public static String getRandomString() {
        return null;
    }
}
