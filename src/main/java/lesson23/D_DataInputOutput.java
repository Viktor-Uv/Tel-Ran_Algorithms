package lesson23;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class D_DataInputOutput {
    public static void main(String[] args) {
        write();
        read();
    }

    public static void write() {
        try (
                FileOutputStream fos = new FileOutputStream(
                        "./src/main/java/lesson23/files/data.bin"
                );
                DataOutputStream dos = new DataOutputStream(fos); // lets us write primitive and String types
        ) {
            // writes bytes, not symbols
            dos.writeDouble(3.14);
            dos.writeBoolean(true);
            dos.writeInt(33);
            dos.writeFloat(.25f);
            dos.writeUTF("Hello, World!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void read() {
        // считайте из файла данные в том порядке и с теми типами как они были записаны
        // и выведите на экран
        try (
                FileInputStream fis = new FileInputStream(
                        "./src/main/java/lesson23/files/data.bin"
                );
                DataInputStream dis = new DataInputStream(fis);
        ) {
            System.out.println(
                    dis.readDouble() + "\n" +
                    dis.readBoolean() + "\n" +
                    dis.readInt() + "\n" +
                    dis.readFloat() + "\n" +
                    dis.readUTF()
            );
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
