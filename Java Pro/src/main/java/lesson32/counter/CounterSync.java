package lesson32.counter;

public class CounterSync implements Counter {
    private int counter = 0;

    @Override
    public int getValue() {
        // верните значение
        return counter;
    }

    @Override
    public synchronized void run() { // синхронизация на экземпляре класса CounterSync
        // увеличьте counter 1_000_000 раз
        for (int i = 0; i < 1_000_000; i++) {
            counter++;
        }
    }
}
