package lesson11;

public class CustomDequeImpl implements CustomDeque {

    private int[] source; // содержимое
    private int size = 0; // начальный размер
    private int firstElementIndex = 0; // индекс первого элемента

    @Override
    public String toString() {
        // [1, 2, 3]
        String r = "[";
        for (int i = 0; i < size(); i++) {
            int v = source[(firstElementIndex + i) % source.length];
            r += v;
            if (i < size() - 1) {
                r += ", ";
            }
        }
        r += "]";
        return r;
    }

    private static final int CAPACITY = 4; // начальный размер массива
    public CustomDequeImpl() {
        source = new int[CAPACITY];
    }


    @Override
    public void addFirst(int i) {
        // нужно проверить не равен ли size размеру массива
        if (size == source.length) {
            // если да, то нужно пересоздать массив и скопировать в него элементы
            increaseCapacity();
        }

        // поменять firstElementIndex
        firstElementIndex = (firstElementIndex - 1 + source.length) % source.length;
        // (0 - 1 + 4) = 3  |  3 % 4 = 3  :  {0, 0,  ,  } -> {0, 0,  , 1}
        // (1 - 1 + 4) = 4  |  4 % 4 = 0  :  { , 0,  , 0} -> {1, 0,  , 0}
        // (3 - 1 + 4) = 6  |  6 % 4 = 2  :  {0,  ,  , 0} -> {0,  , 1, 0}

        // добавить элемент в нужное место
        source[firstElementIndex] = i;

        // увеличить size
        size++;
    }

    private void increaseCapacity() {
        int [] newSource = new int[source.length * 2];
        for (int i = 0; i < size(); i++) {
            newSource[i] = source[(firstElementIndex + i) % source.length];
        }
        firstElementIndex = 0;
        source = newSource;
    }

    @Override
    public int getFirst() throws IndexOutOfBoundsException {
        if (size() == 0) {
            // нужно проверить size()
            throw new IndexOutOfBoundsException();
        } else {
            // если size подходящий, то вернуть "первый"
            return source[firstElementIndex];
        }
    }

    @Override
    public int removeFirst() throws IndexOutOfBoundsException {
        if (size() == 0) {
            // нужно проверить размер и при необходимости выбросить исключение
            throw new IndexOutOfBoundsException();
        }
        // сохранить значение
        int temp = source[firstElementIndex];
        // поправить firstElementIndex
        firstElementIndex = (firstElementIndex + 1) % source.length;
        // уменьшить size
        size--;
        // вернуть значение
        return temp;
    }

    @Override
    public void addLast(int i) {
        // выполнить increaseCapacity если в этом есть необходимость
        if (size() == source.length) {
            increaseCapacity();
        }
        // вычислить индекс элемента в source куда будет происходить вставка
        int lastElementIndex = (firstElementIndex + size()) % source.length;
        // добавить значение по этому индексу
        source[lastElementIndex] = i;
        // увеличить size
        size++;
    }

    @Override
    public int getLast() throws IndexOutOfBoundsException {
        if (size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        return source[(firstElementIndex + size() - 1) % source.length];
    }

    @Override
    public int removeLast() throws IndexOutOfBoundsException {
        /*
        if (size() == 0) {
            // выбросить исключение если есть необходимость
            throw new IndexOutOfBoundsException();
        }
        // определить индекс последнего элемента
        int lastElementIndex = (firstElementIndex + size() - 1) % source.length;
        // сохранить значение последнего элемента во временную переменную
        int temp = source[lastElementIndex];
        // уменьшить размер
        size--;
        // вернуть сохраненное значение
        return temp; */
        int temp = getLast();
        size--;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }
}
