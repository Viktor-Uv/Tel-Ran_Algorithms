package lesson11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetRemember {
    public static void main(String[] args) {
        // HashSet, LinkedHashSet,ThreeSet
        Set<String> countries = new HashSet<>();
        // добавление
        countries.add("Brazil");
        countries.add("Chile");
        // добавление из коллекции
        countries.addAll(Arrays.asList("Monaco", "France", "Belgium"));
        // количество
        System.out.println(countries.size());
        // проверка на наличие
        System.out.println(countries.contains("France"));
        // удаление элемента
        countries.remove("Monaco");
        countries.add("Belgium");
        System.out.println(countries.size());

        // удаление всех элементов из коллекции
        countries.removeAll(Arrays.asList("France", "Belgium"));
        System.out.println(countries);


        Computer c1 = new Computer("XXX-25", "IBM Thinkpad 4", 1200);
        Computer c2 = new Computer("RETCB-22-15", "Olivetti Primo", 1290);
        Computer c3 = new Computer("XXX-25", "Apple MacBook Pro 16 2022", 1900);

        // create HashSet for computers, add, and print
        Set<Computer> computers = new HashSet<>();
        computers.add(c1);
        computers.add(c2);
        computers.add(c3);
        for (Computer c : computers) {
            System.out.println(c);
        }

        String line = "один раз это один раз и один раз отмерь и один раз отрежь";
        // return unique words
        Set<String> uniqueWords = new LinkedHashSet<>();
        uniqueWords.addAll(Arrays.asList(line.split(" ")));
        System.out.println(uniqueWords);

        // посчитать количество вхождений в строку каждого из уникальных слов
        // один:4
        // раз:4
        // ...
        Set<Word> words = new HashSet<>();
        for (String w : line.split(" ")) {
            Word currentWord = findWord(new Word(w), words);
            currentWord.setCount(currentWord.getCount() + 1);
            words.add(currentWord);
        }
        System.out.println(words);





    } // main

    public static Word findWord(Word word, Set<Word> words) {
        for (Word currentWord : words) {
            if (currentWord.equals(word)) {
                return currentWord;
            }
        }
        return word;
    }

}

class Word {
    private String word;
    private int count; // 0

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Word(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", count=" + count +
                '}';
    }
}
