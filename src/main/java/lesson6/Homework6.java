package lesson6;

/**
 * Algorithms. Homework #6
 *
 * @author Viktor Uvarchev
 * @version 08 Aug 2023
 */

public class Homework6 {
    public static void main(String[] args) {
        MyDynamicArray dynArray = new MyDynamicArray();
        dynArray.add(1);
        dynArray.add(1);
        dynArray.add(2);
        dynArray.add(3);
        System.out.println(dynArray); // [1, 1, 2, 3]
        dynArray.add(0, 0);
        System.out.println(dynArray); // [0, 1, 1, 2, 3]
        System.out.println("Remove: " + dynArray.remove(1)); // Remove: 1
        System.out.println(dynArray); // [0, 1, 2, 3]
    }
}

class MyDynamicArray {
    private int[] container;
    private int size;

    public MyDynamicArray() {
        container = new int[4];
        size = 0;
    }

    public MyDynamicArray(int size) {
        this.container = new int[size];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        return container[index];
    }

    private void extendContainer() {
        int[] newArray = new int[container.length * 2];
        System.arraycopy(container, 0,
                newArray, 0,
                container.length);
        container = newArray;
    }

    public void add(int value) {
        if (container.length == size()) {
            extendContainer();
        }
        container[size++] = value;
    }

    public void add(int index, int value) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (container.length == size()) {
            extendContainer();
        }
        System.arraycopy(container, index,
                container, index + 1,
                container.length - index - 1);
        container[index] = value;
        size++;
    }

    public int remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int value = get(index);
        System.arraycopy(container, index + 1,
                container, index,
                container.length - index - 1);
        size--;
        return value;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < size(); i++) {
            sb.append(container[i]);
            if (i < size() - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
