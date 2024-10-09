package lesson23;

import java.io.*;

public class E_ObjectInputOutput {
    public static void main(String[] args) {
        write();
        read();
    }

    public static void write() {
        Dog dog = new Dog("Sharik", 4, false);
        try (
                FileOutputStream fos = new FileOutputStream(
                        "./src/main/java/lesson23/files/dog.bin"
                );
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(dog);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void read() {
        // считать и распечатать Шарика
        try (
                FileInputStream fis = new FileInputStream(
                        "./src/main/java/lesson23/files/dog.bin"
                );
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            Object o = ois.readObject();
            // Check that Object read is the Dog's instance
            if (o instanceof Dog) {
                // Convert Object to Dog and print
                Dog dog = (Dog) o;
                System.out.println(dog);
            }
        }
//        catch (IOException | ClassCastException e) {}
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
