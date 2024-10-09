package lesson10;

/**
 * Algorithms. Homework #10
 *
 * @author Viktor Uvarchev
 * @version 08 Sep 2023
 */

public class Homework10 {
    public static void main(String[] args) {
        HashTable<String, String> hashTable = new HashTable<>(4);
        hashTable.put("Kiev", "Ukraine");
        hashTable.put("Kiev", "Ukraine1");
        hashTable.put("Paris", "France");
        hashTable.put("Berlin", "Germany");
        hashTable.put("Prague", "Czech Republic");
        hashTable.put("Warsaw", "Poland");
        hashTable.put("Madrid", "Spain");
        System.out.println(hashTable.get("Kiev"));
        System.out.println(hashTable.get("Paris"));
        System.out.println(hashTable.get("Berlin"));
        System.out.println(hashTable.get("Prague"));
        System.out.println(hashTable.get("Warsaw"));
        System.out.println("remove: " + hashTable.remove("Madrid"));
        System.out.println(hashTable);
    } // Main

    public static class HashTable<K, V> {
        // Default capacity setting
        private int capacity;
        private Entry<K, V>[] table;
        private int size = 0;

        public HashTable() {
            // Default capacity setting
            this(16);
        }

        public HashTable(int capacity) {
            // Custom capacity setting
            this.capacity = capacity;
            this.table = new Entry[capacity];
        }

        public Entry<K, V>[] getTable() {
            return table;
        }

        private int getIndex(K key) {
            int hash = key.hashCode();
            // Get hash code using bitwise AND operation
            return hash & (capacity - 1);
        }

        public void put(K key, V value) {
            // Get hash code and index if the key
            int idx = getIndex(key);
            // Create a new key:value entry
            Entry<K, V> entry = new Entry<>(key, value);
            if (table[idx] == null) { // If value at index is null, insert the entry there
                table[idx] = entry;
            } else { // Otherwise - check every Entry in the linked list
                Entry<K, V> prevPointer;
                Entry<K, V> pointer = table[idx];
                do { // If the key already exists, update its value and return
                    if (pointer.key.equals(key)) {
                        pointer.value = value;
                        return;
                    }
                    // Keep track of the previous pointer and move to the next Entry
                    prevPointer = pointer;
                    pointer = pointer.next;
                } while (pointer != null);
                // If the key does not exist, add the Entry at the end of the list
                prevPointer.next = entry;
            }
            // Increase size
            size++;
        }

        public V get(K key) {
            // Get hash code and index if the key
            int idx = getIndex(key);
            if (table[idx] == null) { // If value at index is null, return null
                return null;
            }
            // Otherwise - check every Entry in the linked list
            Entry<K, V> pointer = table[idx];
            do {
                // If key matches, return its value
                if (pointer.key.equals(key)) {
                    return pointer.value;
                }
                // Move to the next Entry
                pointer = pointer.next;
            } while (pointer != null);
            // If the key does not exist, return null
            return null;
        }

        public V remove(K key) {
            // Get hash code and index if the key
            int idx = getIndex(key);
            if (table[idx] == null) { // If value at index is null, return null
                return null;
            }
            // Otherwise - check every Entry in the linked list
            Entry<K, V> prevPointer = null;
            Entry<K, V> pointer = table[idx];
            // Find a key to remove
            do {
                // If the key matches, save its value and remove the Entry
                if (pointer.key.equals(key)) {
                    V value = pointer.value;
                    // Remove Entry by linking previous Entry to the next Entry
                    if (prevPointer == null) { // Start at index
                        table[idx] = pointer.next;
                    } else { // Set previous Entry
                        prevPointer.next = pointer.next;
                    }
                    // Reduce size and return deleted value
                    size--;
                    return value;
                }
                // Save previous pointer and move to the next Entry
                prevPointer = pointer;
                pointer = pointer.next;
            } while (pointer != null);
            // If the key does not exist, return null
            return null;
        }

        // Represent HashTable as a string
        @Override
        public String toString() {
            int counter = 0;
            StringBuilder sb = new StringBuilder("{");
            for (Entry<K, V> entry : table) {
                if (entry != null) {
                    Entry<K, V> pointer = entry;
                    do {
                        sb.append(pointer.key + "=" + pointer.value);
                        counter++;
                        if (counter < size) {
                            sb.append(", ");
                        }
                        pointer = pointer.next;
                    } while (pointer != null);
                }
            }
            return sb.append("}").toString();
        }

        // Class for HashTable entries
        public class Entry<K, V> {
            K key; // Entry key
            V value; // Entry value
            Entry<K, V> next; // Link to the next Entry

            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        } // Entry class
    } // HashTable class
}
