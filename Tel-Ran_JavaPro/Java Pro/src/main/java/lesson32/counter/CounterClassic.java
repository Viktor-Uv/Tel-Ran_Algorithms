package lesson32.counter;

public class CounterClassic implements Counter {
    private int counter = 0;

    @Override
    public int getValue() {
        // верните значение
        return counter;
    }

    @Override
    public void run() {
        // увеличьте counter 1_000_000 раз
        for (int i = 0; i < 1_000_000; i++) {
            counter++;
        }
    }
}
