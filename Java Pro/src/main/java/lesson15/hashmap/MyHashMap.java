package lesson15.hashmap;

public class MyHashMap implements MyMap {

    private int size = 0; // количество пар в мапе

    private static class Pair {
        String key;
        String value;
        Pair next; // ссылка на следующую пару

        public Pair(String key, String value, Pair next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value + "}";
        }
    }

    // начальный размер массива
    private static final int INITIAL_CAPACITY = 4;

    // массив с парами
    private Pair[] source = new Pair[INITIAL_CAPACITY];

    // коэффициент загрузки
    // если size > LOAD_FACTOR * source.length
    // то мапу нужно перебалансировать, чтобы стремиться к сложности O(1),
    // чтобы пары попадали в бакеты, а не в линкед-листы
    // перебалансировка - это создание массива в 2 раза больше
    // и копирование туда пар
    private static final double LOAD_FACTOR = 0.75;

    @Override
    public int size() {
        return size;
    }

    // вычисление номера ведра по ключу
    // хэш-функция
    // Object -> int
    // получение индекса ведра:
    // int % source.length
    private int findBucket(String key) {
        return Math.abs( // avoid negative values
                key.hashCode() // String already has a hashCode function
        ) % source.length;
    }

    // нахождение пары по ключу
    private Pair findPair(String key) {
        Pair current = source[findBucket(key)];
        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean contains(String key) {
        return findPair(key) != null;
    }

    @Override
    public void put(String key, String value) {
        if (size() > LOAD_FACTOR * source.length) {
            rebalance();
        }

        Pair pair = findPair(key);
        if (pair != null) { // if such pair exists:
            // просто меняем значение
            pair.value = value;
        } else { // create new pair
            int bucket = findBucket(key);
            source[bucket] = new Pair(
                    key,
                    value,
                    source[bucket] // новая пара становится в корень массива,'смещая' список
            );
            size++;
        }
    }

    private void rebalance() {
        Pair[] newSource = new Pair[2 * source.length];
        for(Pair currentPair : source) { // iterate buckets in the original array
            while (currentPair != null) { // iterate pairs in a given bucket
                Pair savedCurrentsNextPair = currentPair.next; // save origin's next pairs

                int bucket = Math.abs( // find bucket in the new array of buckets
                        currentPair.key.hashCode()
                ) % newSource.length;

                currentPair.next = newSource[bucket]; // save existing pairs in the bucket (if any)
                newSource[bucket] = currentPair; // reassign bucket with new pair-->existing pairs
                currentPair = savedCurrentsNextPair; // move origin's 'next' pairs one step up
            }
        }
        source = newSource; // reassign old source
    }

    // Возвращение значения из пары по ключу
    @Override
    public String get(String key) {
        Pair pair = findPair(key);
        if (pair == null) {
            return null;
        }
        return pair.value;
    }

    @Override
    public String remove(String key) {
        // Returns:
        // the previous value associated with key, or null if there was no mapping for key.

        int bucket = findBucket(key); // find bucket where the key is stored
        Pair currentPair = source[bucket];

        // 1st case: when bucket is empty
        if (currentPair == null) {
            return null;
        }

        // 2nd case: when wanted pair is in the head of the pair array
        if (currentPair.key.equals(key)) {
            source[bucket] = currentPair.next;
            size--;
            return currentPair.value;
        }

        // 3rd case: when wanted pair is within LinkedList
        while (currentPair.next != null) {
            if(currentPair.next.key.equals(key)) {
                Pair toDelete = currentPair.next;
                currentPair.next = toDelete.next;
                size--;
                return toDelete.value;
            }
            currentPair = currentPair.next;
        }

        // 4th case: when no value found
        return null;
    }

    @Override
    public String toString() {
        String r = "[";
        int s = size() - 1;
        for (Pair current : source) {
            while (current != null) {
                r += current;
                if (--s >= 0) {
                    r += ", ";
                }
                current = current.next;
            }
        }
        r += "]";
        return r;
    }
}
