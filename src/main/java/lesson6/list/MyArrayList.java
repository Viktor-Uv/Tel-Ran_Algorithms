package lesson6.list;

import java.util.Iterator;

public class MyArrayList implements MyList, Iterable<Integer> {

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
