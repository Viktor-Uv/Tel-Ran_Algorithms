package lesson8;

import lesson6.list.MyList;

import java.util.Iterator;
import java.util.List;

// ArrayList - на основе массива
// LinkedList - каждый элемент хранит ссылки на следующий и предыдущий

// доступ по индексу
// ArrayList O(1)
// LinkedList O(N)

// работа с первым и последним элементом - добавление, изменение, удаление
// ArrayList O(N)
// LinkedList O(1)

public class MyLinkedList implements MyList {


    private Node head; // голова списка - первый узел
    private int size = 0; // количество элементов в списке

    @Override
    public String toString() {
        // [1,2,3]
        String r = "[";
        Node n = head;
        while (n != null) {
            r += n.getValue();
            n = n.getNext();
            if (n != null) {
                r += ", ";
            }
        }
        r += "]";
        return r;
    }

    private class Node {
        private int value; // значение элемента
        private Node next; // ссылка на следующий узел

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(int value) {
        Node n = head;
        while (n != null) {
            if (n.getValue() == value) {
                return true;
            }
            n = n.getNext();
        }
        return false;
    }

    @Override
    public int returnMin() {
        return 0;
    }

    @Override
    public int returnMax() {
        return 0;
    }

    @Override
    public void set(int index, int value) {
        getNodeByIndex(index).setValue(value);
    }

    @Override
    public void add(int value) {
        size++;
        //  head == null
        if (head == null) {
            head = new Node(value);
            return;
        }

        //  head != null
        Node n = head;
        while (n.getNext() != null) {
            n = n.getNext();
        }
        n.setNext(new Node(value));
    }

    // получение узла по его индексу
    private Node getNodeByIndex (int index) {
        Node n = head;
        for (int i = 0; i < index; i++) {
            if (n != null) {
                n = n.getNext();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        if (n == null) {
            throw new IndexOutOfBoundsException();
        }
        return n;
    }

    @Override
    public void addAll(List<Integer> list) {

    }

    @Override
    public void add(int index, int value) {
        if (index == 0) {
             Node prevHead = head;
             head = new Node(value, prevHead);
            // head = new Node(value, head);
        } else {
            Node prev = getNodeByIndex(index - 1);
            Node next = prev.getNext();
            Node newNode = new Node(value, next);
            prev.setNext(newNode);
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            if (head == null) {
                throw new IndexOutOfBoundsException();
            }
            head = head.getNext();
            size--;
            return;
        }
        Node prev = getNodeByIndex(index - 1); // select previous
        Node current = prev.getNext(); // select one to be deleted (through previous)
        // Node current = getNodeByIndex(index);
        if (current != null) {
            prev.setNext(current.getNext());
        }
        size--;
    }

    // получение значения элемента по его индексу
    @Override
    public int get(int index) {
        return getNodeByIndex(index).getValue();
    }

    @Override
    public Iterator<Integer> reversedIterator() {
        return null;
    }

    @Override
    public Iterator<Integer> ascendingIterator() {
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    // добавление элемента в начало списка
    void addFirst(int e) {
        add(0, e);
    }

    // получение значения первого элемента
    int getFirst() {
        return get(0);
    }

    // удаление первого элемента с возвращением его "старого" значения
    int removeFirst() {
        int deleted = getFirst();
        remove(0);
        return deleted;
    }

}
