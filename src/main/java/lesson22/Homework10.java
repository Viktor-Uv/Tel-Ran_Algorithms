package lesson22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

/**
 * JavaPro. Homework #10
 *
 * @author Viktor Uvarchev
 * @version 09 Sep 2023
 */

/*
 * Task 1. Дан файл (names.txt) со списком имен на выбор, некоторые имена повторяются.
 *              === names.txt начало ===
 *              Max Alexander Lena
 *              Sveta
 *              Alexander Dima Lena Max
 *              Sveta Pavel
 *              === names.txt окончание ===
 *         Написать метод, который вернет количество вхождений каждого из имен в файл
 * Task 2. Есть текстовой файл с произвольным текстом text.txt
 *         Найдите самое длинное слово (слова отделяются между собой пробелами).
 */

public class Homework10 {
    public static void main(String[] args) {
        // Task 1:
        Map<String, Integer> namesCount = new HashMap<>();
        try (
                Reader reader = new FileReader("./src/main/java/lesson22/files/names.txt");
                BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            // Read one line
            String line = bufferedReader.readLine();
            do {
                // Break this line into individual words
                for (String name : line.split(" ")) {
                    if (!namesCount.containsKey(name)) {
                        // Put new key in Map
                        namesCount.put(name, 1);
                    } else {
                        // Update existing key
                        int previousValue = namesCount.get(name);
                        namesCount.put(name, ++previousValue);
                    }
                }
                // Read next line
                line = bufferedReader.readLine();
            } while (line != null); // Until end of the file reached
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        System.out.println("Task 1: " + namesCount);
        // Output-> Task 1: {Alexander=2, Max=2, Sveta=2, Lena=2, Dima=1, Pavel=1}


        // Task 2:
        String longestWord = null;
        try (
                Reader reader = new FileReader("./src/main/java/lesson22/files/text.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            // Read one line
            String line = bufferedReader.readLine();
            Set<String> set = new HashSet<>();
            do {
                // Break this line into individual words and put in HashSet
                set.addAll(Arrays.asList(line.split(" ")));
                // Read next line
                line = bufferedReader.readLine();
            } while (line != null); // Until end of the file reached
            // Find the longest word
            longestWord = set.stream()
                    .max(Comparator.comparingInt(String::length))
                    .orElse(null);
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        System.out.println("Task 2: " + longestWord);
        // Output-> Task 2: Rinderkennzeichnungsfleischetikettierungsüberwachungsaufgabenübertragungsgesetz

    }
}
