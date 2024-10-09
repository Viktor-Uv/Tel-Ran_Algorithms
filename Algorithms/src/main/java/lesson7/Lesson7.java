package lesson7;

import java.util.LinkedList;

/**
 * Algorithms Lesson #7. Stack and Queue
 * @version 11 Aug 2023
 */

public class Lesson7 {
    public static void main(String[] args) {
        LinkedList<Integer> stackQueue = new LinkedList<>();

        // LinkedList as Stack
        stackQueue.push(5);
        System.out.println(stackQueue);
        stackQueue.push(12);
        System.out.println(stackQueue);
        stackQueue.push(-2);
        System.out.println(stackQueue);
        for (int i = 0, n = stackQueue.size(); i < n; i++) {
            System.out.println(stackQueue.pop() + " <- " + stackQueue);
        }

        // LinkedList as Queue
        stackQueue.offer(8);
        System.out.println(stackQueue);
        stackQueue.offer(-5);
        System.out.println(stackQueue);
        stackQueue.offer(11);
        System.out.println(stackQueue);
        for (int i = 0, n = stackQueue.size(); i < n; i++) {
            System.out.println(stackQueue.poll() + " <- " + stackQueue);
        }

    }
}
