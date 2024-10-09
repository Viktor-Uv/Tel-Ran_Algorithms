package lesson24;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Arrays;
import java.util.stream.IntStream;

public class C_Dir {
    public static void main(String[] args) throws IOException {
        /*
        A File object is a representation of a file or directory in the Java program,
        and it provides methods to manipulate and access its properties.
         */
        File file = new File("."); // create a File object in memory using a relative
                                            // path "." for the current working directory
        String path = file.getAbsolutePath(); // get the absolute path of the File Object
        printDir(0, path);
    }

    public static void printDir(int level, String path) throws IOException {
        File file = new File(path); // create a File object for the given path
        File[] files = file.listFiles(); // get an array of all files in the directory
        if (files != null) {
            Arrays.sort(files); // sort the array by name
            for (File f : files) { // for each file in the array
                System.out.print(!f.isDirectory() ? "-" : "d");
                // r - если можно файл читать
                // w - если можно в файл писать
                // x - если можно файл исполнять
                // - - если это нельзя делать
                // -rw- text.txt
                // drwx target
                System.out.print(f.canRead() ? "r" : "-");
                System.out.print(f.canWrite() ? "w" : "-");
                System.out.print(f.canExecute() ? "x" : "-");

                // для получения дополнительной информации о
                // правах доступа/владельцах и тп
                FileOwnerAttributeView av = Files.getFileAttributeView(
                        Paths.get(f.getAbsolutePath()),
                        FileOwnerAttributeView.class
                ); // get the file owner attribute view for the file

                System.out.printf("\t%8d\t%12s", f.length(), av.getOwner().getName()); // print the size and
                                                                        // owner of the file with formatting

                IntStream.rangeClosed(0, level)
                        .forEach(
                                i -> System.out.print("\t")
                        ); // print tabs according to the level of indentation

                System.out.println(f.getName()); // print the name of the file

                if (f.isDirectory()) {
                    printDir(level + 1, f.getAbsolutePath()); // recursively call the printDir method
                                                        // with an increased level and the subdirectory path
                }
            } // end of for-each loop
        } // end of if
    } // Main
}
