package lesson23;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class B_CopyWithBuffer {
    public static void main(String[] args) {
        try (
                InputStream fis = new FileInputStream("./src/main/java/lesson23/files/digits.txt");
                OutputStream fos = new FileOutputStream("./src/main/java/lesson23/files/copy-of-digits.txt")
        ) {
            // буфер куда будут считываться данные из файла
            byte[] buffer = new byte[10];
            int numberOfBytesRead = 0;
            while ((numberOfBytesRead = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, numberOfBytesRead);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
