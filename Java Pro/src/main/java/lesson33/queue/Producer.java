package lesson33.queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        long before = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            Message message = new Message("index: " + i);
            try {
                // подождите время i*100
                Thread.sleep(i * 100);
                // положите сообщение в очередь
                // put блокирует поток до момента добавления сообщения в очередь
                queue.put(message);
                // распечатайте временной интервал между текущим временем и сохраненным временем
                System.out.println("Produced: " + message.getMsg() + " time is: "
                        + (System.currentTimeMillis() - before));
            } catch (Exception ignored) {}
        }
        // положите в очередь сообщение "exit"
        try {
            queue.put(new Message("exit"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
