package lesson15; // Date 14.08.2023

/*
 * контейнер Map хранит в себе набор пар из ключа и значения (ссылочных типов)
 * Map хранит пары с уникальными ключами
 * HashMap - основана на хешировании ключа для ускорения хранения и поиска
 * LinkedHashMap - все то же самое + гарантируется порядок обхода - в порядке вставки
 * TreeMap - хранит все элементы в порядке заданном компаратором для ключа
 *
 * добавление пары в Map - .put(..., ...)
 * значение по ключу - .get(...)
 * получение и итерация по всем ключам - .keySet(), returns Set of keys
 * returns Collection of values - .values()
 * удаление пары по ключу .remove(...)
 * Map.Entry<> - интерфейс для пары; AbstractMap.SimpleEntry<> - реализация; .entrySet() - pair iteration
 *
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/images/Collection_MapImplementation.png
 * https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
 */

import java.util.*;

public class Lesson15 {
    public static void main(String[] args) {
        Map<String, String> capitals = new HashMap<>();
        capitals.put("Hungary", "Budapest"); // добавление пары в Map - .put(..., ...)
        capitals.put("Poland", "Warsaw");
        capitals.put("Estonia", "Tallinn");
        capitals.put("Austria", "Vena");
        capitals.put("France", "Paris");
        capitals.put("Finland", "Helsinki");
        System.out.println("Size: " + capitals.size());
        capitals.put("France", "Paris");
        System.out.println("Size: " + capitals.size());
        System.out.println("Capital of Poland: " + capitals.get("Poland")); // значение по ключу - .get(...)
        System.out.println("Capital of England: " + capitals.get("England")); // null
        System.out.println("Содержится ли запись с ключом Estonia: " + capitals.containsKey("Estonia"));
        System.out.println("Содержится ли запись со значением London: " + capitals.containsValue("London"));

        // получение и итерация по всем ключам - .keySet()
        for (String key : capitals.keySet()) { // returns Set of keys
            System.out.println("key is: " + key);
        }

        // capitals.values() значения
        // распечатайте все столицы в которых есть i
        // "".contains("")
        List<String> countries = new ArrayList<>(capitals.keySet());
        for (String value : capitals.values()) { // returns Collection of values
            if (value.contains("i")) {
                System.out.println("Capital contains 'i': " + value);
            }
        }

        capitals.remove("Austria"); // удаление пары по ключу .remove(...)
        System.out.println(capitals.size());
        capitals.remove("France");
        System.out.println(capitals.size());
        System.out.println(capitals);

        // создайте на базе списка countries Map<String, Integer> countriesLength
        // где по ключу-названию страны хранилась бы длина строки-названия
        Map<String, Integer> countriesLength = new HashMap<>();
        for (String country : countries) {
            countriesLength.put(country, country.length());
        }
        System.out.println(countriesLength);

        // https://www.google.com/search?client=firefox-b-d&q=fruits+calories
        // на базе информации из ссылки
        // создайте Map<String, Integer> calories
        // ключ - название фрукта значение - калорийность
        Map<String, Integer> calories = new HashMap<>();
        calories.put("Apple", 52);
        calories.put("Grapes", 67);
        calories.put("Kiwi", 61);
        calories.put("Orange", 47);
        calories.put("Plums", 46);
        calories.put("Quince", 57);
        calories.put("Bananas", 89);
        System.out.println(calories);

        // итерация по парам
        // Map.Entry<> - интерфейс для пары
        // AbstractMap.SimpleEntry<> - реализация
        for(Map.Entry<String, Integer> entry : calories.entrySet()) {
            System.out.println("pair: " + entry + "; key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        // Create any pair:
        Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>("Germany", "Berlin");
        System.out.println(entry);

        System.out.println(findMax(calories));

        // Map as database (kind of)
        List<Integer> number = Arrays.asList(1, 2, 1, 4, 4, 5, 4, 7, 1);
        Map<Integer, Integer> numbersCounter = new HashMap<>();
        for (Integer n : number) {
            // нужно найти в numbersCounter значение по числу
            if (numbersCounter.get(n) == null) {
                // если значение есть, сохранить пару увеличив значение на 1
                numbersCounter.put(n, 1);
            } else {
                numbersCounter.put(n, numbersCounter.get(n) + 1);
            }
        }
        System.out.println(numbersCounter); // {1=3, 2=1, 4=3, 5=1, 7=1}

    } // Main

    // напишите функцию, которая вернет самый калорийный фрукт из calories
    public static String findMax(Map<String, Integer> calories) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(calories.entrySet());
        return Collections.max(list,
                (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())
        ).getKey();

//        String max = null;
//        int c = 0;
//        for (String s : calories.keySet()) {
//            if (max == null) {
//                max = s;
//                c = calories.get(max);
//            } else if (calories.get(s) > c) {
//                c = calories.get(s);
//                max = s;
//            }
//        }
//        return max;
    }
}
