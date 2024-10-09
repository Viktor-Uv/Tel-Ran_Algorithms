package lesson32.counter;

public class TestCounter {
    public static void main(String[] args) {
//        Counter counter = new CounterClassic();
        // создайте класс CounterSync
        // и поправьте в нем этот недостаток
        // запустите с ним main и убедитесь что проблема решена

//        Counter counter = new CounterSync();
        Counter counter = new CounterAtomic();

        Thread t1 = new Thread(counter);
        Thread t2 = new Thread(counter);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.getValue());
        // (CounterClassic) 1091264 and varies
        // (CounterSync) 2000000
        // (CounterAtomic) 2000000

    }
}
