package lesson28.generic;

public class PairImpl<K, V> implements Pair<K, V> {
    private K first;
    private V second;

    public PairImpl(K first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override // override what is declared in the Interface
    public K first() {
        return first;
    }

    @Override
    public V second() {
        return second;
    }

    @Override
    public String toString() {
        return "P{" +
                "f=" + first +
                ", s=" + second +
                '}';
    }
}
