package lesson11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        // Stack - элементы добавляются и получаются с одной стороны
        // LIFO - Last in First Out

        // T push(T) - добавление элемента в верх стека
        // T pop() - удаление и возвращение элемента с вершины стека
        // T peek() - возвращение элемента с вершины стека без удаления
        // boolean empty()
        // int size()

        Stack<String> names = new Stack<>();
        names.add("Max");
        names.add("Dina");
        names.add("Dasha");
        names.add("Masha");

        // напишите код, который распечатывает все элементы со стека
        while (!names.empty()) {
            System.out.println(names.pop());
        }

        //  возвращает на какой глубине находится элемент
        System.out.println(names.search("Max")); // -1 - такого элемента в стэке нет

        String line = "однажды в студеную зимнюю пору я из лесу вышел";
        // print in reverse:
        Stack<String> words = new Stack<>();
        for (String word : line.split(" ")) {
            words.push(word);
        }
        while (!words.empty()) {
            System.out.print(words.pop() + " ");
        }
        System.out.println();

        // 10 20 30 40 50 60 70 80 90
        // 60 70 80 90 50 40 30 20 10
        // 50 40 30 20 10 60 70 80 90
        Queue<Integer> intQueue = new LinkedList<>(
                Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90)
        );
        Stack<Integer> intStack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            intStack.push(intQueue.poll());
        }
        for (int i = 0; i < 5; i++) {
            intQueue.offer(intStack.pop());
        }
        for (int i = 0; i < 4; i++) {
            intQueue.offer(intQueue.poll());
        }
        System.out.println(intQueue);
    }
}
