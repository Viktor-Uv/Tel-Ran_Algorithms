package lesson7;

import lesson6.list.MyArrayList;
import lesson6.list.MyList;

import java.util.*;

public class IteratorTester {
    public static void main(String[] args) {
        List<String> capitals = new ArrayList<>(Arrays.asList("Bogota", "Brazilia", "Buenos-Aires", "Santiago"));
        Iterator<String> capitalsIterator = capitals.iterator();
        // прежде чем обращаться к итератору за элементом
        // нужно спросить у него, а есть ли он: hasNext()?
        while (capitalsIterator.hasNext()) {
            System.out.println("element is: " + capitalsIterator.next());
        }

        List<Integer> integerList = new ArrayList<>(Arrays.asList(-2, 1, 2, 3, 4, 5, 6, 7));
        Iterator<Integer> intIterator = integerList.iterator();
        while (intIterator.hasNext()) {
            System.out.println("int is: " + intIterator.next());
        }

        MyList myList = new MyArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        Iterator<Integer> myListIterator = myList.iterator();

        while (myListIterator.hasNext()) {
            System.out.println("next int is: " + myListIterator.next());
        }

        List<String> rivers = new ArrayList<>();
        rivers.add("Rein");
        rivers.add("Vistula");
        rivers.add("Oder");
        rivers.add("Danube");
        rivers.add("Sena");
        rivers.add("Ruhr");

        for (int i = 0; i < rivers.size(); i++) {
            System.out.println("Standard for: " + rivers.get(i));
        }
        for (String r : rivers) {
            System.out.println("Foreach: " + r);
        }

        // итератор - механизм перебора элементов любого контейнера

        // создайте массив целых с несколькими значениями
        // и переберите его с помощью for-each
        int [] digits = {1,2,3,4,5};
        for(int element: digits) {
            System.out.println("digits element: " + element);
        }

        // класс, который хотим перебирать с помощью for-each
        // должен реализовывать интерфейс Iterable<T>
        for(Integer i: myList) {
            System.out.println("myList for-each element: " + i);
        }

        Set<String> stringSet = new HashSet<>(Arrays.asList("A", "B", "C"));
        System.out.println(stringSet.contains("E"));
        Iterator<String> setIterator = stringSet.iterator();
        while (setIterator.hasNext()) {
            System.out.println("set element: " + setIterator.next());
        }


    }
}
