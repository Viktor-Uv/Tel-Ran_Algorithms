package lesson22;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class C_CopyFile {
    public static void main(String[] args) {
        int i;
        try(
                // блок инициализации переменных которые реализуют интерфейс AutoClosable
                FileInputStream fis = new FileInputStream("./src/main/java/lesson22/files/test.txt");
                FileOutputStream fos = new FileOutputStream("./src/main/java/lesson22/files/copy-of-test.txt")
                ) {
            // try-блок где происходит вся работа
            // -1 - сигнал, что байты во входном потоке закончились
            // fis.read() - считывает и возвращает 1 байт
            // fis.read(byte[]) - считывает данные в массив и возвращает количество считанных байтов
            while ( (i = fis.read()) != -1 ) {
                fos.write(i);
            }
        }
        catch (IOException e) {
            // catch-блок, где обрабатываются исключения
            System.err.println("IO Exception: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        finally {
            // выполняется после try или catch блока
        }
    }
}
