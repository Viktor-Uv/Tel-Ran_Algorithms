package lesson7.homework4;

import lesson6.list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * JavaPro. Homework #4
 *
 * @author Viktor Uvarchev
 * @version 13 Jul 2023
 */

/*
 * Task 1. Есть два класса : Address с полями улица и номер дома и Persons
 * с полями String name и Address address.
 * Напишите метод List<Address> getAddresses(List<Person> persons)
 * то есть по списку persons возвращать список их адресов
 *
 * Task 2. Есть два списка одинаковой длины с числами.
 * Напишите метод, возвращающий список с элементами Yes или No
 * где значение на і-том месте зависит от того, равны ли элементы двух списков под номером і  -
 * например, {1,2,3,4,12} и {5,2,3,3,0}->{"No", "Yes", "Yes", "No", "No"}
 *
 * Task 3. Напишите функцию, меняющую порядок следования элементов в списке на противоположный -
 * например, {1,2,3} -> {3,2,1}
 *
 * Task 4. Напишите функцию, возвращающую второй по величине элемент списка целых -
 * например, {1,3,4,2} -> 3
 *
 * Task *5. Добавить в MyArrayList/MyList функцию для получения итератора, обходящего список
 * в обратном порядке - от последнего элемента к первому
 *
 * Task **6. Добавить в MyArrayList/MyList функцию для получения итератора, обходящего элементы
 * списка по возрастанию их значений, от меньшего к большему
 */

public class Homework {
    public static void main(String[] args) {
        // Test for Task 1
        List<Persons> personsList = new ArrayList<>(Arrays.asList(
                new Persons("Bob", new Address("Berlin Str", 1)),
                new Persons("Joe", new Address("Hamburg Str", 3)),
                new Persons("Tim", new Address("Dusseldorf Str", 6))
        ));
        System.out.println("Task 1: " + getAddresses(personsList));

        // Test for Task 2
        List<Integer> testList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 12));
        List<Integer> testList2 = new ArrayList<>(Arrays.asList(5, 2, 3, 3, 0));
        System.out.println("Task 2: " + compareEachElement(testList1, testList2));

        // Test for Task 3
        List<Integer> testList3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        reverseList(testList3);
        System.out.println("Task 3: " + testList3);

        // Test for Task 4
        List<Integer> testList4 = new ArrayList<>(Arrays.asList(5, 8, -2, 0, -3, 0, 7));
        System.out.println("Task 4: " + returnSecondLargest(testList4));

        // Test for Task *5
        MyArrayList testList5 = new MyArrayList();
        testList5.addAll(testList3); // copy of testList3, that has been reversed in Task 3
        Iterator<Integer> testIterator1 = testList5.reversedIterator();
        System.out.print("Task 5: ");
        while (testIterator1.hasNext()) {
            System.out.print(testIterator1.next() + " ");
        }
        System.out.println(); // Outputs testList3 with elements in its original order

        // Test for Task **6
        MyArrayList testList6 = new MyArrayList();
        testList6.addAll(testList4); // copy of testList4
        Iterator<Integer> testIterator2 = testList6.ascendingIterator();
        System.out.print("Task 6: ");
        while (testIterator2.hasNext()) {
            System.out.print(testIterator2.next() + " ");
        }
        System.out.println(); // Outputs testList4 with elements in ascending order


    }
    // Method for Task 1
    public static List<Address> getAddresses(List<Persons> persons) {
        List<Address> addressList = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            addressList.add(persons.get(i).getAddress());
        }
        return addressList;
    }

    // Method for Task 2
    public static List<String> compareEachElement(List<Integer>list1, List<Integer>list2) {
        if (list1.size() != list2.size()) {
            throw new IndexOutOfBoundsException();
        }
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            resultList.add(
                    list1.get(i).equals(list2.get(i)) ? "Yes" : "No");
        }
        return resultList;
    }

    // Method for Task 3
    public static void reverseList(List<Integer> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            int temp = list.get(list.size() - 1 - i);
            list.set(list.size() - 1 - i, list.get(i));
            list.set(i, temp);
        }
    }

    // Method for Task 4
    public static int returnSecondLargest(List<Integer> list) {
        int largest = list.get(0);
        int secondLargest = list.get(0);
        for (int element : list) {
            if (secondLargest >= largest) {
                secondLargest = element;
            }
            // Find largest
            if (largest < element) {
                largest = element;
            }
            // Find second largest
            if (secondLargest < element && element < largest) {
                secondLargest = element;
            }
        }
//        for (int element : list) {
//
//        }
        return secondLargest;
    }
}
