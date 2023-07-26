package lesson6.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyArrayList implements MyList, Iterable<Integer> {

    // Homework 4, Task *5
    public Iterator<Integer> reversedIterator() {
        return new Iterator<Integer>() {
            private int position = size;

            @Override
            public boolean hasNext() {
                return --position >= 0;
            }

            @Override
            public Integer next() {
                return get(position);
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(position++);
            }
        };
    }

    // Homework 4, Task **6
    public Iterator<Integer> ascendingIterator() {
        return new Iterator<Integer>() {
            private int position = -1;
            private final int[] sortedArray = copyOfList();

            @Override
            public boolean hasNext() {
                return ++position < size;
            }

            @Override
            public Integer next() {
                return sortedArray[position];
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(position--);
            }

            private int[] copyOfList() {
                int[] copy = new int[size];
                for (int i = 0; i < size; i++) {
                    copy[i] = get(i);
                }
                Arrays.sort(copy);
                return copy;
            }
        };
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            // номер текущего элемента
            private int position = -1;

            @Override
            public boolean hasNext() {
                return ++position < size;
            }

            @Override
            public Integer next() {
                return get(position);
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(position--);
            }
        };
    }

    // количество заполненных элементов, видимый пользователю размер контейнера
    private int size = 0;
    private int [] data; // массив в котором будут храниться элементы
    // начальный размер массива
    private static final int INITIAL_CAPACITY = 4;

    public MyArrayList() {
        data = new int[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(int value) {
        for (int i = 0; i < size(); i++) {
            if (data[i] == value)
                return true;
        }
        return false;
    }

    @Override
    public int returnMin() {
        int min = get(0);
        for (int i = 0; i < size; i++) {
            if (min > get(i)) {
                min = get(i);
            }
        }
        return min;
    }

    @Override
    public int returnMax() {
        int max = get(0);
        for (int i = 0; i < size; i++) {
            if (max < get(i)) {
                max = get(i);
            }
        }
        return max;
    }

    @Override
    public void set(int index, int value) {
        // проверить что index находится в диапазоне 0 <= index < size
        // если не так, выбросим исключение
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        // change value
        data[index] = value;
    }

    @Override
    public void add(int value) {
        // добавление элемента в конец
        // если size() == data.length то нужно
        if (size() == data.length) {
            increaseCapacity();
        }
        // добавить в конец элемент value
        data[size] = value;
        // увеличить size
        size++;
    }

    // Homework 4, Task *5
    @Override
    public void addAll(List<Integer> list) {
        // добавление списка элементов в конец
        for (int element : list) {
            if (size() == data.length) {
                increaseCapacity();
            }
            // добавить в конец элемент value
            data[size] = element;
            // увеличить size
            size++;
        }
    }

    private void increaseCapacity() {
        // нужно создать массив в 2 раза больше
        int[] temp = new int[data.length * 2];

        // скопировать элементы от 0 до data.length из старого массива в новый
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }

        // присвоить data ссылку на новый массив
        data = temp;
    }

    @Override
    public void add(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size() == data.length) {
            increaseCapacity();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        for (int i = index + 1; i < size(); i++) {
            data[i - 1] = data[i];
        }
        size--;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return data[index];
    }

    @Override
    public String toString() {
        String r = "[";
        for (int i = 0; i < size(); i++) {
            if (i != 0) {
                r += ", ";
            }
            r += data[i];
        }
        r += "]";
        return r;
    }
}
