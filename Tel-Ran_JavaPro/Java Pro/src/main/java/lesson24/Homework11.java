package lesson24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * JavaPro. Homework #11
 *
 * @author Viktor Uvarchev
 * @version 17 Sep 2023
 */

/*
 * Task 1. Напишите функцию, которая считает количество строк в
 *         передаваемом в нее в виде параметра текстовом файле
 *
 * Task 2. В функцию передаются имя файла и подстрока. Посчитайте количество
 *         строк текстового файла, содержащие эту подстроку.
 *
 * Task 3*. Доделайте E_Concordance
 */

public class Homework11 {
    public static void main(String[] args) {
        // Task 1 test:
        try {
            printLineCount("./src/main/java/lesson24/files/cars.txt");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Task 2 test:
        try {
            printSubstringLineCount("./src/main/java/lesson24/files/cars.txt", "maker");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Task 3: please, follow the link below and see the line 62
        /* https://github.com/Viktor-Uv/Tel-Ran_JavaPro/blob/main/src/main/java/lesson24/E_Concordance.java */

    } // Main

    // Task 1 implementation:
    public static void printLineCount(String path) throws Exception {
        try (
                Reader fileReader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            System.out.println(
                    bufferedReader.lines()
                            .count()
            );
        }
    }

    // Task 2 implementation:
    public static void printSubstringLineCount(String path, String substring) throws Exception {
        try (
                Reader fileReader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            System.out.println(
                    bufferedReader.lines()
                            .filter(line -> line.contains(substring))
                            .count()
            );
        }
    }

}
