package lesson15;

import com.sun.source.tree.Tree;

import java.util.*;

public class Tasks {
    public static void main(String[] args) {
        String words = "hello hell exactly eye robot hi";
        System.out.println(mapFirstLetterToList(words));

        Map<String, String> brandToModel = new HashMap<>();
        brandToModel.put("BMW", "Z3");
        brandToModel.put("Peugeot", "407");
        brandToModel.put("Renault", "Logan");
        // создайте и распечатайте мапу из модели в марку
        Map<String, String> modelToBrand = new HashMap<>();
        for (String s : brandToModel.keySet()) {
            modelToBrand.put(brandToModel.get(s), s);
        }
        System.out.println(modelToBrand);

        System.out.println(frequency("один раз это один раз"));

        System.out.println(getWordLengthFrequency(Arrays.asList(
                "Max", "Daria", "Masha", "Alex", "Ron"
        )));

        Map<String, String> tickets = new HashMap<>();
        tickets.put("Berlin", "London");
        tickets.put("Tokyo", "Seoul");
        tickets.put("Mumbai", "Berlin");
        tickets.put("Oslo", "Paris");
        tickets.put("Seoul", "Mumbai");
        findRoute("Tokyo", "London", tickets);


        // Map Implementation diagram:
        // https://www3.ntu.edu.sg/home/ehchua/programming/java/images/Collection_MapImplementation.png

        // LinkedHashMap - гарантирует что элементы при обходе будут возвращаться
        // в порядке вставки

        // TreeSet - элементы при обходе будут возвращаться в порядке
        // определяемом компаратором для ключа (по умолчанию - в порядке возрастания)

        System.out.println(tickets); // HashMap - вразнобой

        TreeMap<String, String> orderedTickets = new TreeMap<>(tickets);
        System.out.println(orderedTickets); // TreeMap - по возрастанию

        TreeMap<String, String> orderedByKeyLengthTickets = new TreeMap<>(
                Comparator.comparingInt(String::length)
        );

        orderedByKeyLengthTickets.putAll(tickets);
        System.out.println(orderedByKeyLengthTickets);

    } // Main

    // написать функцию для подбора сложного маршрута
    public static  void findRoute(String from, String to, Map<String, String> tickets) {
        if (tickets.get(from).equals(to)) {
            System.out.println(from + " -> " + to);
            return;
        }
        System.out.println(from + " -> " + tickets.get(from));
        findRoute(tickets.get(from), to, tickets);
    }

    // напишите функцию, которая подсчитывает статистику распределения слов по длине
    // ключ - длина слова, значение - количество слов такой длины
    // [Max, Daria, Masha, Alex, Ron] -> {3:2, 4:1, 5:2}
    public static Map<Integer, Integer> getWordLengthFrequency(List<String> words) {
        Map<Integer, Integer> statistics = new HashMap<>();
        for (String word : words) {
            int key = word.length();
            Integer value = statistics.get(key);
            if (value == null) {
                statistics.put(key, 1);
            } else {
                statistics.put(key, ++value);
            }
        }
        return statistics;
    }

    // напишите функцию, которая посчитывает количество вхождений слова в строку
    // "один раз это один раз" -> {один:2, раз:2, это:1}
    public static Map<String, Integer> frequency(String words)
    {
        Map<String, Integer> result = new HashMap<>();
        // разбейте строку на слова по пробелу
        // в цикле пройдите по словам
        for (String word : words.split(" ")) {
            // получите по слову количество раз из мапы
            Integer quantity = result.get(word);
            if (quantity == null) {
                // если оно null, то считайте что это 1
                result.put(word, 1);
            } else {
                // если не null, увеличьте на 1,
                // добавьте пару из слова->количества в мапу
                result.put(word, ++quantity);
            }
        }
        return result;
    }

    // написать функцию которая создаст Map<String, List<String>>
    // где ключом будет первая буква слова а значением список слов в котором
    // эта буква первая
    // { h:{hello, hell, hi}, e:{exactly, eye}, r:{robot}}
    public static Map<String, List<String>> mapFirstLetterToList(String words) {
        Map<String, List<String>> result = new HashMap<>();
        for(String word : words.split(" ")) {
            String key = word.substring(0, 1); // first letter of each word
            // проверим значение из мапы по первой букве слова
            List<String> value = result.get(key);
            if(value == null) {
                // если списка нет, создаем его
                value = new ArrayList<>();
            }
            // добавляем в список слово
            value.add(word);
            // сохраняем в мапу по ключу обновленный список
            result.put(key, value);
        }
        return result;
    }
}
