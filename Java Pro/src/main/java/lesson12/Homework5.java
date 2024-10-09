package lesson12;

import java.util.*;

/**
 * JavaPro. Homework #5
 *
 * @author Viktor Uvarchev
 * @version 07 Aug 2023
 */

/*
 * Task 1. Есть список стран countries = ["Andorra", "Belize", "Cayman", "France", "Argentina", "Cuba", "Sweden"]
 *          и есть список слов words = ["Andorra", "Canada", "First", "candy", "Argentina", "wood", "parrot", "cat",
 *           "Alan", "Cuba", "Finland", "Axelrod" , "Avangard", "Cuba"]
 *          нужно получить список всех стран из списка слов, начинающихся на "A" - т.е., ["Argentina", "Andorra"]
 *
 * Task *2. Реализуйте стэк используя LinkedList
 *          https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html
 *
 * Task 3. Напишите метод, который принимает на вход строку и возвращает количество уникальных символов в ней
 *
 * Task 4. Напишите функцию, принимающую на вход массив целых и целое число.
 *          Функция должна возвращать true если среди чисел массива есть пара,
 *          произведение которых дает второй аргумент
 *          Подумайте об оптимальном алгоритм сравнения
 *          например: [8,12,10,5], 60 -> true так как 12*5=60
 *
 * Task 5. Напишите программу, которая удаляет все отрицательные числа из заданного сета
 */

public class Homework5 {
    public static void main(String[] args) {
        // Task 1
        System.out.println("Task 1:\n" + returnAnswerTask1() + "\n");

        // Task 2
        CustomIntStackImpl customIntStack = new CustomIntStackImpl();
        customIntStack.push(90);
        customIntStack.push(70);
        customIntStack.pop();
        customIntStack.push(40);
        System.out.println("Task 2:\n" + customIntStack + "\n");

        // Task 3
        String string = "Andorra, Canada, First, candy, Argentina, wood, parrot, cat, Alan, Cuba, Finland, " +
                "Axelrod, Avangard, Cuba, Andorra, Belize, Cayman, France, Argentina, Cuba, Sweden";
        System.out.println("Task 3:\n" + returnUniqueSymbolsCount(string) + "\n");

        // Task 4
        System.out.println("Task 4:\n" + returnAnswerTask4(new int[]{8, 12, 10, 5}, 60) + "\n");

        // Task 5
        Set<Integer> integerSet = new LinkedHashSet<>(Arrays.asList(8, 12, 10, 5, -12, 9, -1, 0, 76, 99, -5, -11));
        removeNegativeElements(integerSet);
        System.out.println("Task 5:\n" + integerSet);

    } // main

    // Task 1
    public static Set<String> returnAnswerTask1() {
        List<String> countries = new ArrayList<>(Arrays.asList("Andorra", "Belize", "Cayman", "France",
                "Argentina", "Cuba", "Sweden"));
        List<String> words = new ArrayList<>(Arrays.asList("Andorra", "Canada", "First", "candy", "Argentina",
                "wood", "parrot", "cat", "Alan", "Cuba", "Finland", "Axelrod" , "Avangard", "Cuba"));

        Set<String> commonFromA = new HashSet<>();
        commonFromA.addAll(countries);
        commonFromA.retainAll(words);

        Iterator<String> commonIterator = commonFromA.iterator();
        while (commonIterator.hasNext()) {
            if (commonIterator.next().toCharArray()[0] != 'A') {
                commonIterator.remove();
            }
        }

        return commonFromA;
    }

    // Task 2
    interface CustomIntStack {
        boolean empty(); // Tests if this stack is empty
        int push(int value); // Pushes an item onto the top of this stack
        int pop(); // Removes the object at the top of this stack and returns that object as the value of this function
        int peek(); // Looks at the object at the top of this stack without removing it from the stack
        int search(int value); // Returns the 1-based position where an object is on this stack
        int size(); // Returns this stack size

    }
    static class CustomIntStackImpl implements CustomIntStack {
        private Node head; // head of the linked list
        private int size = 0; // stack size

        private class Node {
            private int value; // element's value
            private Node previous; // link to previous element

            public Node(int value) {
                this.value = value;
            }
            public Node(int value, Node previous) {
                this.value = value;
                this.previous = previous;
            }
            public int getValue() {
                return value;
            }
            public void setValue(int value) {
                this.value = value;
            }
            public Node getPrevious() {
                return previous;
            }
            public void setPrevious(Node next) {
                this.previous = next;
            }
        }

        @Override
        public String toString() {
            String r = "[";
            Node n = head;
            while (n != null) {
                r += n.getValue();
                n = n.getPrevious();
                if (n != null) {
                    r += ", ";
                }
            }
            r += "]";
            return r;
        }

        @Override
        public boolean empty() {
            return size() == 0;
        }

        @Override
        public int push(int value) {
            if (head == null) {
                head = new Node(value);
            } else {
                Node previousHead = head;
                head = new Node(value, previousHead);
            }
            size++;
            return value;
        }

        @Override
        public int pop() {
            if (empty()) {
                throw new EmptyStackException();
            }
            int value = peek();
            head = head.getPrevious();
            size--;
            return value;
        }

        @Override
        public int peek() {
            if (empty()) {
                throw new EmptyStackException();
            }
            return head.getValue();
        }

        @Override
        public int search(int value) {
            Node n = head;
            int counter = 0;
            while (n != null) {
                counter++;
                if (value == n.getValue()) {
                    return counter;
                }
                n = n.getPrevious();
            }
            return -1;
        }

        @Override
        public int size() {
            return size;
        }
    }

    // Task 3
    public static int returnUniqueSymbolsCount(String input) {
        return new HashSet<>(
                Arrays.asList(input.split(""))
        ).size();
    }

    // Task 4
    public static boolean returnAnswerTask4(int[] array, int number) {
        Set<Integer> integerSet = new HashSet<>();
        for (int i : array) {
            integerSet.add(i);
        }
        // special case: number = 0
        if (number == 0 && array.length > 1) {
            if (integerSet.contains(number)) {
                return true;
            }
        }
        // normal case
        for (int i : integerSet) {
            if (i != 0 && number % i == 0) {
                if (integerSet.contains(number / i)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Task 5
    public static void removeNegativeElements(Set<Integer> integerSet) {
        Iterator<Integer> integerIterator = integerSet.iterator();
        while (integerIterator.hasNext()) {
            if (integerIterator.next() < 0) {
                integerIterator.remove();
            }
        }
    }
}
