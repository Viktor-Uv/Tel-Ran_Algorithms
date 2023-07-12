package lesson7;

import java.util.Iterator;

public class Practice {
    public static void main(String[] args) {
        int[] digits = {1, 2, 3};
        Iterator<Integer> integerIterator = new ArrayIterator(digits);
        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }

        StringIterator stringIterator = new StringIterator("Hello");
        while (stringIterator.hasNext()) {
            System.out.println("character is: " + stringIterator.next());
        }

        // char[] symbols = "123".toCharArray();
    }
}
// реализовать StringIterator
class StringIterator implements Iterator<Character> {
//    private String string;
    private char[] symbols;
    private int position = -1;

    public StringIterator(String string) {
//        this.string = string;
        this.symbols = string.toCharArray();
    }

    @Override
    public boolean hasNext() {
        return ++position < symbols.length;
    }

    @Override
    public Character next() {
        return symbols[position];
    }
}



// реализуйте Iterator по массиву целых
class ArrayIterator implements Iterator<Integer> {
    private int[] data;
    private int position = -1;

    public ArrayIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return ++position < data.length;
    }

    @Override
    public Integer next() {
        return data[position];
    }
}