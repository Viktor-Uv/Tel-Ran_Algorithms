package lesson11;

public class CustomDequeImpl implements CustomDeque {

    private int[] source; // содержимое
    private int size = 0; // начальный размер
    private int firstElementIndex = 0; // индекс первого элемента

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
    }

    @Override
    public int getFirst() throws IndexOutOfBoundsException {
        return 0;
    }

    @Override
    public int removeFirst() throws IndexOutOfBoundsException {
        return 0;
    }

    @Override
    public void addLast(int i) {

    }

    @Override
    public int getLast() throws IndexOutOfBoundsException {
        return 0;
    }

    @Override
    public int removeLast() throws IndexOutOfBoundsException {
        return 0;
    }

    @Override
    public int size() {
        return size;
    }
}
