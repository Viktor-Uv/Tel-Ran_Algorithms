package lesson31;

public class D_SynchronizationTester {

    public static int[] data = {0};

    public static void main(String[] args) throws InterruptedException {
        long before = System.currentTimeMillis();

//        Runnable r = new CalculationJob();
//        for (int i = 0; i < 5; i++) {
//            new Thread(r).start();
//        }

        CalculationJob runnable = new CalculationJob();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);
        Thread t5 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // join позволяет дождаться окончания выбранного потока
        // join может блокировать поток, в котором выполняется
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        // race condition - "состояние гонки"

        System.out.println("result: " + data[0]);
        long after = System.currentTimeMillis();
        System.out.println("time:  " + (after - before));
    } // Main

//    public static void add() {
//        data[0]++;
//    }

//    public static synchronized void add() {
//        data[0]++;
//    }

    // в чем цель синхронизации:
    // разрешить одному потоку (или ограниченному количеству потоков)
    // одновременно выполнять какой-то "блок" кода
    // {}
    // "блоком" также может быть функция

    // на чем можно синхронизироваться
    // 1. на любом объекте
    /*
        synchronized (lock) { // 340 ms
            data[0]++;
        }
     */
    // 2. на классе
    // public static synchronized void add()
    // 3. на экземпляре класса в котором функция объявлена
    // public synchronized void remove()

    // synchronized - только один поток сможет получить доступ в эту функцию
    // на время пока один поток выполняет функцию, остальные ждут
    // синхронизация происходит на классе SynchronizationTester
    // после того как поток покинул блок синхронизации,
    // операционная система случайным образом выбирает поток для выполнения
    // блока синхронизации из числа "ждущих"
    public static /*synchronized*/ void add() {
        synchronized (lock) {
            data[0]++;
        }
    }

    // можно создать отдельный объект для синхронизации
    private static final Object lock = new Object();

    static class CalculationJob implements Runnable {
        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            for (int i = 0; i < 1_000_000; i++) {
                add();
            }
            System.out.println("thread " + id + " finished, result is " + data[0]);
        }
    }
}
