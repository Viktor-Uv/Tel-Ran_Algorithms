package lesson24;

// A concordance is a type of reference book that lists the words or phrases in a text
// or a collection of texts, along with their contexts and meanings.

/*
this is a cool climate // line 0
hate this climate // line 1
this brings relief to this pal // line 2
cool hate // line 3
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class E_Concordance {
    public static void main(String[] args) {

        // создайте Map<String, List<Integer>>
        // List<Integer>> - номера строк в которых это слово встречалось
        // "this" -> [0, 1, 2]
        // "cool" -> [0, 3]

        // считайте строчки
        // превратите их в слова
        try (
                // Create a file reader and a buffered reader to read the text file
                Reader fileReader = new FileReader(
                        "./src/main/java/lesson24/files/concordance.txt"
                );
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {

            // bad method, it leads to a lost line indexes:
//            bufferedReader.lines()
//                    .flatMap(line -> Arrays.stream(line.split(" ")))
//                    .forEach(System.out::println);


            // Read all the lines from the file and store them in a list of lines
            List<String> lines =
                    bufferedReader.lines()
                            .toList();

            // .rangeClosed() creates a stream of integers from 0 to the size of the list of lines:
            IntStream.rangeClosed(0, lines.size() - 1) // 0, 1, 2, 3
                    // Map each line index to a pair of the index and the corresponding line
                    .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, lines.get(i))) // 3-> "cool hate"
                    // Flatten the pairs into pairs of words and line indexes
                    .flatMap(
                            p -> Arrays.stream(p.getValue().split(" ")) // stream of words
                                    .map(w -> new AbstractMap.SimpleEntry<>(w, p.getKey()))
                    ) // "cool" -> 3

                    // ==== Homework 11 ====
                    // создайте Map<String, List<Integer>>
                    // List<Integer>> - номера строк в которых это слово встречалось
                    // "this" -> [0, 1, 2]
                    // "cool" -> [0, 3]
                    .collect(Collectors.groupingBy(
                            // Group the entries of the map by their keys
                            AbstractMap.SimpleEntry::getKey,
                            // Transform stream of map entries into a stream of their values and collect them
                            Collectors.mapping(
                                    // Collect the values in a LinkedHashSet to preserve order
                                    AbstractMap.SimpleEntry::getValue,
                                    Collectors.toCollection(LinkedHashSet::new)
                            )
                    ))
                    // Print key:value pairs each on a new line
                    .forEach(
                            (k, v) -> System.out.println(k + ":" + v)
                    );

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
