package lesson33;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * JavaPro. Homework #17
 *
 * @author Viktor Uvarchev
 * @version 15 Oct 2023
 */

/*
 * Task:
 * Создайте класс BankAccount c приватным целым полем balance, добавьте геттер и сеттер.
 * Добавьте метод increaseBalance, принимающий целое число, на которое будет увеличиваться
 * баланс банковского счета.
 *
 * Создайте производный от Runnable класс BankAccountRunnable, который принимает в конструкторе
 * BankAccount и 10 раз увеличивает его баланс на 10 единиц, каждый раз перед увеличением
 * ожидая рандомное время от 0 до 100 миллисекунд.
 *
 * В main создайте один экземпляр BankAccount.
 * В main создайте один экземпляр класса BankAccountRunnable, передав туда ссылку на BankAccount
 * и запустите его 5 раз на пуле потоков с 3 тредами.
 *
 * Распечатайте баланс банковского счета, дождавшись остановки пула потоков.
 *
 * Поправьте класс BankAccount чтобы распечатывалось правильное значение.
 */

public class Homework17 {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BankAccountRunnable accountRunnable = new BankAccountRunnable(bankAccount);

        ExecutorService service = Executors.newFixedThreadPool(3); // Create pull with 3 threads
        for (int i = 0; i < 5; i++) {
            service.submit(accountRunnable); // Start Runnable 5 times
        }
        service.shutdown();

        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(bankAccount.getBalance());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Results without 'synchronized': 480, 470, 490, 500, 500, 490 (inconsistent)
        // Results with 'synchronized': 500 (consistent)

    }

    public static class BankAccount {
        private int balance;

        public int getBalance() {
            return balance;
        }

        private void setBalance(int balance) {
            this.balance = balance;
        }

//        public void increaseBalance(int amount) { // without 'synchronized' - unreliable results
//            setBalance(getBalance() + amount);
//        }

        public synchronized void increaseBalance(int amount) { // added 'synchronized'
            setBalance(getBalance() + amount);
        }
    }

    public static class BankAccountRunnable implements Runnable {
        private BankAccount account;
        private Random r = new Random();

        public BankAccountRunnable(BankAccount account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) { // increase in 10 steps by 10 units (100 units in total)
                try {
                    Thread.sleep(r.nextInt(101)); // Random delay from 0 to 100 ms
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                account.increaseBalance(10);
            }
        }
    }
}
