package lesson24;

import java.io.File;
import java.util.Arrays;

public class B_File {
    public static void main(String[] args) {
        File dogBin = new File("./src/main/java/lesson23/files/dog.bin"); // create a File object
                                                                                   // for a binary file
        System.out.println("exists: " + dogBin.exists()); // exists: true
        System.out.println("length: " + dogBin.length()); // length: 90

        File currentDir = new File("."); // create a File object for the root directory
        if (currentDir.isDirectory()) {
            System.out.println("is directory"); // is directory
            String[] files = currentDir.list(); // get an array of file & folder names in the current directory
            System.out.println(Arrays.asList(files)); // [.git, .gitignore, .idea, pom.xml, src, target]

            // dogBin.delete()
            // dogBin.renameTo()

            Arrays.stream(files)
                    .forEach(
                            f -> {
                                // каждый файл распечатайте на отдельной строке
                                // с префиксом d для директории и
                                // f для не директории
                                // f names.txt
                                // ...
                                // d src
                                File file = new File(f);
                                if (file.isDirectory()) {
                                    System.out.print("d ");
                                } else {
                                    System.out.print("f ");
                                }
                                System.out.println(f);
                            }
                    );
        }
    }
}
