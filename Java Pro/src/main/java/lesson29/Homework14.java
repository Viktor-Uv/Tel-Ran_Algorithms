package lesson29;

import lesson28.generic.list.MyGenericList;

/**
 * JavaPro. Homework #14
 *
 * @author Viktor Uvarchev
 * @version 01 Oct 2023
 */

/*
 * Task 1. Сделайте на шаблонах вариант LinkedList (8 занятие) и/или CustomDeque (11 занятие)
 * Task 2. Напишите тесты для проверки правильности их работы
 */

public class Homework14 {
    // Generic LinkedList implementation:
    public static class MyGenericLinkedList<T> implements MyGenericList<T> {
        private Node<T> head; // голова списка - первый узел
        private int size = 0; // количество элементов в списке

        private class Node<T> {
            private T value; // значение элемента
            private Node<T> next; // ссылка на следующий узел

            public Node(T value) {
                this.value = value;
            }

            public Node(T value, Node<T> next) {
                this.value = value;
                this.next = next;
            }

            public T getValue() {
                return value;
            }

            public void setValue(T value) {
                this.value = value;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }
        } // Node Class

        private Node<T> getNodeByIndex (int index) {
            Node<T> n = head;
            // Try to iterate over every Node until index is reached
            for (int i = 0; i < index; i++) {
                if (n == null) {
                    break;
                }
                n = n.getNext();
            }
            return n;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean contains(T value) {
            Node<T> n = head;
            while (n != null) {
                if (n.getValue().equals(value)) {
                    return true;
                }
                n = n.getNext();
            }
            return false;
        }

        @Override
        public void set(int index, T value) {
            Node<T> n = getNodeByIndex(index);
            if (n == null) {
                return;
            }
            n.setValue(value);
        }

        @Override
        public void add(T value) {
            if (size() == 0) {
                // when head == null
                head = new Node<>(value);
            } else {
                // when head != null
                getNodeByIndex(size() - 1).setNext(new Node<>(value));
            }
            size++;
        }

        @Override
        public void add(int index, T value) {
            if (index == 0) {
                Node<T> prevHead = head;
                head = new Node<>(value, prevHead);
            } else {
                Node<T> prev = getNodeByIndex(index - 1);
                Node<T> newNode = new Node<>(value, prev.getNext());
                prev.setNext(newNode);
            }
            size++;
        }

        @Override
        public void remove(int index) {
            if (head == null) {
                return;
            } else if (index == 0) {
                // set the head node to the head->next
                head = head.getNext(); // head - is the Node to be deleted
            } else {
                // Get Node at the previous index that will skip over the Node at the given index
                Node<T> prev = getNodeByIndex(index - 1);
                if (prev != null) {
                    // get the Node to be deleted (at the given index)
                    Node<T> current = prev.getNext(); // current - is the Node to be deleted
                    if (current != null) {
                        // Skip over the current Node
                        prev.setNext(current.getNext());
                    }
                }
            }
            // Decrement the size of the LinkedList if remove() completed successfully
            size--;
        }

        @Override
        public T get(int index) {
            Node<T> n = getNodeByIndex(index);
            if (n == null) {
                return null;
            } else {
                return n.getValue();
            }
        }

        @Override
        public String toString() {
            StringBuilder r = new StringBuilder("[");
            Node<T> n = head;
            while (n != null) {
                r.append(n.getValue());
                n = n.getNext();
                if (n != null) {
                    r.append(", ");
                }
            }
            r.append("]");
            return r.toString();
        }
    } // MyGenericLinkedList Class

    // Task 2 -> please, go to the test folder:
    /*
    https://github.com/Viktor-Uv/Tel-Ran_JavaPro/blob/main/src/test/java/lesson29/Homework14Test.java
     */
}
