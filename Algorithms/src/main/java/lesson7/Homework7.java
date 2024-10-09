package lesson7;

import java.util.EmptyStackException;

/**
 * Algorithms. Homework #7
 *
 * @author Viktor Uvarchev
 * @version 20 Aug 2023
 */

public class Homework7 {
    public static void main(String[] args) {
        MyDynamicList myDynamicList = new MyDynamicList();
        myDynamicList.push(1);
        myDynamicList.push(8);
        myDynamicList.push(-4);
        myDynamicList.push(9);
        System.out.println(myDynamicList); // [9, -4, 8, 1]
        System.out.println("pop(" + myDynamicList.pop() + ") <- " + myDynamicList); // pop(9) <- [-4, 8, 1]
        myDynamicList.offer(9);
        System.out.println(myDynamicList); // [-4, 8, 1, 9]
        System.out.println("poll(" + myDynamicList.poll() + ") <- " + myDynamicList); // poll(-4) <- [8, 1, 9]

    } // Main

    public static class MyDynamicList {
        private int size;
        private Node first;
        private Node last;

        public void push(Integer value) {
            if (first == null) { // push 1st element
                first = new Node(value);
            } else if (last == null) {  // push 2nd element
                last = first;
                first = new Node(value, last);
                last.prev = first;
            } else { // push remaining elements
                Node oldFirst = first;
                first = new Node(value, oldFirst);
                oldFirst.prev = first;
            }
            size++;
        }

        public void offer(Integer value) {
            if (first == null) { // offer 1st element
                first = new Node(value);
            } else if (last == null) { // offer 2nd element
                last = new Node(value, first, null);
                first.next = last;
            } else { // offer remaining elements
                Node oldLast = last;
                last = new Node(value, oldLast, null);
                oldLast.next = last;  // fix link to next element in old 'last'
            }
            size++;
        }

        public Integer pop() { // take one element from head
            if (size == 0) {
                throw new EmptyStackException();
            }
            Integer poppedValue = first.value;
            if (size == 1) {
                first = null;
                last = null;
            } else {
                Node newFirst = first.next;
                newFirst.prev = null; // strip link to previous element from new 'first'
                first = newFirst;
            }
            size--;
            return poppedValue;
        }

        public Integer poll() { // take one element from head
            return pop();
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer("[");
            if (first != null) {
                Node cursor = first;
                do {
                    sb.append(cursor.value);
                    cursor = cursor.next;
                    if (cursor != null) {
                        sb.append(", ");
                    }
                } while (cursor != null);
            }
            sb.append("]");
            return sb.toString();
        }

        private static class Node {
            Integer value;
            Node prev;
            Node next;

            public Node(Integer value, Node prev, Node next) {
                this.value = value;
                this.prev = prev;
                this.next = next;
            }

            public Node(Integer value, Node next) {
                this.value = value;
                this.next = next;
            }

            public Node(Integer value) {
                this.value = value;
            }
        } // class Node
    } // class MyDynamicList
}
