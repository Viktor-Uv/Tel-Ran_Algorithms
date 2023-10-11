package lesson33.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10_000);
            // в цикле while получаем сообщения из очереди
            // пока не встретится сообщение с "exit"
            Message message;
            while (! (message = queue.take()).getMsg().equals("exit")) {
                // сообщим о полученном сообщении
                System.out.println("Consumed: " + message.getMsg());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
