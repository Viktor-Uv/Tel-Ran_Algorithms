package lesson31; // 04.10.2023

/* Проблематика:
1. Пользовательский интерфейс однопоточный
    AWT, Swing, JavaFx
    Android
2. Стандартные классы для работы с многопочностью в яве неудобны
3. Все современные процессоры многоядерные
4. Имеется несколько распространенных библиотек для более удобной работы с многопоточностью
    RxJava3 - java, kotlin, android
    Reactor - spring, kotlin, android
    Coroutine - kotlin, android
5. "Поток"
    stream
    iostream
    thread
 */

/* Терминология:
Процесс - Process - выполняющаяся программа
Рост производительности:
    Увеличить количество CPU - Central Processing Unit - процессор (резко повышается стоимость);
    Увеличить тактовую частоту (на данный момент достигнут технологический предел);
    Увеличить количество ядер на CPU (используется чаще всего).
        Ядро - Core - возможность параллельного выполнения какого-нибудь метода программы
    Поток выполнения, Thread, "Нить" - параллельно выполняющаяся функция

Основной поток - поток выполнения, который получается при старте программы, (1)
Порожденный поток - поток выполнения, запущенный из основного или другого порожденного, (другой идентификатор)
 */

/*
 * Thread: A thread is a lightweight process that has its own call stack but can access shared
 *      data of other threads in the same process. A Java application runs by default in one thread,
 *      but you can create and run multiple threads using the Thread class or the Runnable interface.
 *
 * Runtime: The Runtime class provides access to the Java runtime system, which allows you to interact
 *      with the environment in which your application is running. For example, you can execute
 *      external commands, terminate the JVM, or get information about the available memory.
 *
 * Runnable: The Runnable interface defines a single method, run(), that is intended to contain the
 *      code executed in a thread. The Runnable object is passed to the Thread constructor,
 *      as in: Thread myThread = new Thread(new RunnableTask());.
 *
 * Daemon: A daemon thread is a thread that does not prevent the JVM from exiting when the program
 *      finishes but the thread is still running. An example of a daemon thread is the garbage collector
 *      that frees the memory occupied by objects that are no longer in use. You can use the
 *      setDaemon(true) method to make a thread a daemon thread before starting it.
 */

public class A_ThreadIntro {
    public static void main(String[] args) {
        // у каждого потока выполнения есть свой идентификатор
        System.out.println("Main thread id is: " + Thread.currentThread().getId()); // 1

        // сколько ядер
        // Runtime - чтобы узнать информацию о текущей машине и запустить какие-нибудь команды
        // операционной системы
        System.out.println("Number of cores: " + Runtime.getRuntime().availableProcessors());

        // создание пустого потока
        Thread t1 = new Thread();

        // Thread - шаблон для потока выполнения
        Thread t2 = new Thread() {
            @Override
            public void run() {
                // полезная работа, которую хотим выполнить в порожденном потоке
                System.out.println("Thread with id: " + Thread.currentThread().getId());
            }
        };

        // запуск порожденного потока на выполнение
        t2.start(); // 23
//        t2.start(); // IllegalThreadStateException, because state is not "new"
        // Thread life cycle diagram:
        // https://www.scientecheasy.com/wp-content/uploads/2020/06/thread-life-cycle.png

        // Runnable - работа, которая может быть выполнена в порожденном потоке
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread from runnable, id: " + Thread.currentThread().getId());
            }
        };
        // Create 2 threads to run r1 simultaneously
        new Thread(r1).start(); // Thread from runnable, id: 24
        new Thread(r1).start(); // Thread from runnable, id: 25

        // main по-умолчанию дожидается окончания выполнения всех его порожденных потоков

        new Thread() {
            @Override
            public void run() {
                System.out.println("Slow thread start, id: " + Thread.currentThread().getId()); // 26
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Slow thread end, id: " + Thread.currentThread().getId()); // 26
            }
        }.start();

        // можно "демонизировать" поток выполнения и тогда main не будет его ждать
        Thread daemonThread = new Thread() {
            @Override
            public void run() {
                System.out.println("Daemon thread start, id: " + Thread.currentThread().getId()); // 27
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Daemon thread end, id: " + Thread.currentThread().getId()); // ---
            }
        };
        daemonThread.setDaemon(true); // демонизация - main не будет его ждать
        // приоритет влияет на долю времени, выделяемого потоку на выполнение
        daemonThread.setPriority(Thread.MAX_PRIORITY);
        daemonThread.start();

        Runnable work = A_ThreadIntro::someWork;
        Thread anotherDaemonThread = new Thread(work);
        anotherDaemonThread.setDaemon(true);
        anotherDaemonThread.start();
    }

    public static void someWork() {
        System.out.println("Another daemon thread start, id: " + Thread.currentThread().getId()); // 28
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Another daemon thread end, id: " + Thread.currentThread().getId()); // ---
    }
}
