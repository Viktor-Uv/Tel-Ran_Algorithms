package lesson32.waitnotify;

public class CoffeeMachine {

    private int capsules = 10;

    public synchronized void makeCoffee() { // синхр на экземпляре класса CoffeeMachine
        for (int i = capsules; i >= 0; i--) {
            if (i != 0) {
                System.out.println("Делаем чашку кофе из капсулы " + (capsules - i + 1));
            } else {
                System.out.println("Капсулы закончились");
                capsules = 0;
                try {
                    wait(); // освобождаем объект синхронизации
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("Количество капсул " + capsules);
            }
        }
    }

    public synchronized void reload() {
        System.out.println("Добавляем новые капсулы");
        capsules = 10;
        notify(); // пробуждаем потоки, ждущие на объекты синхронизации
    }

}
