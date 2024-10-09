package lesson32.waitnotify;

public class CoffeeMakerTester {
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();

        // создайте два потока
        // в одном запустите makeCoffee
        Thread t1 = new Thread(machine::makeCoffee);
        // в другом reload
        Thread t2 = new Thread(machine::reload);

        // запустите эти потоки
        t1.start();
        t2.start();

    }
}
