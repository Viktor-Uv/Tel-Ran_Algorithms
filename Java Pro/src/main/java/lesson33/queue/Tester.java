package lesson33.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html

public class Tester {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        System.out.println("Producer and Consumer are running");
    }
}
