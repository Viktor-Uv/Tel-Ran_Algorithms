package lesson34;

public class B_DontDoLikeThis {
    public static void main(String[] args) {
        // Создайте "первый" поток, который вызывает getName
        // и запускает "второй" поток, в котором выполняется getLength,
        //      передавая туда результат getName в качестве параметра
        // во "втором" потоке вызовите "третий" поток, передав туда результат
        //      getLength и вызовите с ним finish()

        Thread t1 = new Thread() {
            @Override
            public void run() {
                String name = getUserName();
                Thread t2 = new Thread() {
                    @Override
                    public void run() {
                        Integer length = getLength(name);
                        Thread t3 = new Thread() {
                            @Override
                            public void run() {
                                finish(length);
                            }
                        };
                        t3.start();
                    }
                };
                t2.start();
            }
        };
        t1.start();

    }

    public static String getUserName() {
        long time = System.currentTimeMillis();
        if (time % 2 == 0) {
            throw new RuntimeException("Uncaught Exception");
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {}
        return "Alexander";
    }

    public static Integer getLength(String s) {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {}
        return s.length();
    }

    public static void finish(Integer i) {
        System.out.println("Result is: " + i);
    }
}
