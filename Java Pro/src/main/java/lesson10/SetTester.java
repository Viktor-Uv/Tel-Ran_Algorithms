package lesson10;

/*
    Set - интерфейс - служит базой для коллекций содержащих уникальные элементы
    применения
        поиск дубликатов
        поиск уникальных элементов

    реализации
        HashSet - набор хранится в виде оптимизированном для поиска
            предполагается что операции поска O(1)
            при этом расходуется память на поддержание внутренней структуры
            порядок обхода не гарантируется
        LinkedHashSet - гарантирует, что при обходе элементы возвращаются в
            порядке вставки
        TreeSet - хранит и возвращает элементы при обходе в порядке сортировки
            заданным для типа либо заданным через компаратор
 */

import com.sun.source.tree.Tree;

import java.util.*;

public class SetTester {
    public static void main(String[] args) {
        Set<String> groups = new HashSet<>(Arrays.asList("Abba", "Beatles", "Rolling Stones"));
        groups.add("Deep Purple");
        groups.add("Led Zeppelin"); // Add
        System.out.println(groups); // [Rolling Stones, Led Zeppelin, Beatles, Deep Purple, Abba]

        System.out.println(groups.contains("Eurythmics")); // false
        System.out.println(groups.contains("Abba")); // true
        System.out.println(groups.size()); // size - 5
        groups.add("Abba"); // duplicates not added
        System.out.println(groups.size()); // size - 5
        System.out.println(groups); // [Rolling Stones, Led Zeppelin, Beatles, Deep Purple, Abba]

        /*
            обход по итератору для сета имет смысл делать только
            если в процессе обхода потребуется что-то удалять
         */
        Iterator<String> groupsIterator = groups.iterator();
        while (groupsIterator.hasNext()) {
            System.out.println("group is: " + groupsIterator.next());
        }

        // во всех остальных случаях - for-each:
        for (String s : groups) {
            System.out.println("for each group: " + s);
        }

        /*
            Set не даёт доступ по индексу -
            применения - contains и обход по итератору или for-each
         */

        groups.remove("Abba");
        System.out.println(groups); // [Rolling Stones, Led Zeppelin, Beatles, Deep Purple]

        /*
             Collection.addAll(Collection c) - добавление элементов из другой коллекции
             Collection.containsAll(Collection c) - true если 'c' содержится полностью
             Collection.removeAll(Collection c) - удаление всех элементов содержащихся в 'c'
             Collection.retainAll(Collection c) - в коллекции останутся только общие элементы
         */

        List<String> britishGroups = Arrays.asList("Blur", "Oasis", "Rolling Stones", "Beatles");
        // Task: remove from 'groups' all 'britishGroups'
        groups.removeAll(britishGroups);
        System.out.println(groups); // [Led Zeppelin, Deep Purple]


        // TreeSet - хранит элементы в порядке сортировки
        TreeSet<String> britishGroupsTreeSet = new TreeSet<>(britishGroups);
        System.out.println(britishGroupsTreeSet); // [Beatles, Blur, Oasis, Rolling Stones]


        /*  TreeSet extra methods:
            headSet - все элементы 'до'
            subSet - все элементы 'от' и 'до'
            tailSet - все элементы 'после'
            floor - самый нижний элемент
         */


        System.out.println(britishGroupsTreeSet.headSet("Guns and Roses")); // [Beatles, Blur]

        System.out.println(britishGroupsTreeSet.subSet("Guns and Roses", "ZZ Top")); // [Oasis, Rolling Stones]

        System.out.println(filterCollection(
                        Arrays.asList(1,2,3,4,5,6,7), 2, 6
        )); // [3, 4, 5] (exclusive)  /  [2, 3, 4, 5] (inclusive)

        System.out.println(getDoubles(
                "dima max sveta lena gena lena sveta max" // [sveta, max, lena]
        ));

        System.out.println(uniqueLetters(
                "hello lake mid" // [h, e, l, o,  , a, k, m, i, d]
        ));

        List<String> months = Arrays.asList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        System.out.println(filterMonth(months));

    } // end of main

    // считается, что устрицы можно есть тольео в месяцы, в которых есть буква "r"
    // return months when you can eat them, in the original order
    public static Set<String> filterMonth(List<String> m) {
        Set<String> filtered = new LinkedHashSet<>();
        for (String month : m) {
            if (month.contains("r")) {
                filtered.add(month);
            }
        }
        return filtered;
    }

    // function that returns set of unique letter from a String in the original order
    // example: "hello lake mid" -> h,e,l,o, ,a,k,m,i,d
    public static Set<String> uniqueLetters(String s) {
//        Set<String> letters = new LinkedHashSet<>();
//        for (String letter : s.split("")) {
//            letters.add(letter);
//        }
//        return letters;
        return new LinkedHashSet<>( // stores in order elements were added
                Arrays.asList(
                        s.split("")
                )
        );
    }

    // function that returns a list of duplicates
    public static Set<String> getDoubles(String s) {
        Set<String> words = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String word : s.split(" ")) {
            if (words.contains(word)) {
                duplicates.add(word);
            }
            words.add(word);
        }
        return duplicates;
    }

    // функция, которая принимает на вход коллекцию и два значения - 'от' и 'до'
    // и возвращает Set с элементами коллекции, лежащими в этом диапазоне (без циклов)
    public static Set<Integer> filterCollection(Collection<Integer> c, int from, int to) {
//        TreeSet<Integer> newSet = new TreeSet<>(c);
//        return newSet.subSet(from, to);

//        return new TreeSet<>(c).subSet(from, to); // inclusive
        return new TreeSet<>(c).subSet(from, false, to, false); // exclusive
    }
}
