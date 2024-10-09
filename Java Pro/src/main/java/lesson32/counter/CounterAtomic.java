package lesson32.counter;

import java.util.concurrent.atomic.AtomicInteger;

// Есть набор "атомарных"
// их операции выполняются правильно в не зависимости от
// количества потоков, которые хотят их выполнить
public class CounterAtomic implements Counter {
    private AtomicInteger counter = new AtomicInteger(); // 0 by default

    @Override
    public int getValue() {
        // верните значение
        return counter.get();
    }

    @Override
    public void run() {
        // увеличьте counter 1_000_000 раз
        for (int i = 0; i < 1_000_000; i++) {
            counter.incrementAndGet(); // увеличить значение и вернуть результат
        }
    }
}
