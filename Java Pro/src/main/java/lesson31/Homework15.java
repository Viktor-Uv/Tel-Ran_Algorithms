package lesson31;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * JavaPro. Homework #15
 *
 * @author Viktor Uvarchev
 * @version 08 Oct 2023
 */

/*
 * Task 1. Напишите шаблонную функцию, которая принимает на вход список чего угодно и varargs из целых.
 *          Нужно вернуть коллекцию из элементов, номера которых и передаются в виде varargs
 *              public static <T> Collection<T> getElements(List<T> list, int ... elements)
 *
 * Task 2*. Напишите тесты чтобы это проверить
 *
 * Task 3. Создайте LocalDateTime с какой-нибудь датой, например с днем рождения
 *          Проверьте, является ли выбранная дата пятницей
 *          Воспользуйтесь DateTimeFormatter, чтобы вывести эту дату в виде
 *              01 January 1970, Thursday
 *          Выведите то-же самое, но по-французски
 *              01 janvier 1970, jeudi
 *
 * Task 4. Создайте Instant из строки "2023-07-13T19:34:00.00Z"
 *          Переведите этот момент времени во временную зону "Pacific/Honolulu" и распечатайте
 *
 * Task 5*. Создайте функцию, ожидающую рандомное время от 0 до 1000 мс
 *          и возвращающую это время в качестве результата
 *              public static int wait()
 *          Запустите эту функцию в двух потоках, в их методе run сохраните результат выполнения
 *          этой функции в статические переменные класса.
 *          В main запустите потоки и распечатайте сумму значений этих статических переменных.
 */

public class Homework15 {
    private static int runResultThread1; // (Task 5)
    private static int runResultThread2; // (Task 5)

    public static void main(String[] args) {
        // Task 3:
        LocalDateTime date = LocalDateTime.of(1992, 8, 31, 0, 0);

        System.out.println(
                "Is it Friday? - " +
                        date.getDayOfWeek().equals(DayOfWeek.FRIDAY) // false
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE");
        System.out.println(date.format(formatter)); // 31 August 1992, Monday

        Locale fr = new Locale("fr", "FR");
        System.out.println(date.format(formatter.withLocale(fr))); // 31 août 1992, lundi

        // Task 4:
        Instant instant = Instant.parse("2023-07-13T19:34:00.00Z");
        ZonedDateTime zoned = instant
                .atZone(
                        ZoneId.of("Pacific/Honolulu")
                );
        System.out.println(zoned); // 2023-07-13T09:34-10:00[Pacific/Honolulu]

        // Task 5:
        Thread t1 = new Thread(
                () -> runResultThread1 = toWait()
        );
        Thread t2 = new Thread(
                () -> runResultThread2 = toWait()
        );

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total wait time: " + (runResultThread1 + runResultThread2 + " ms"));
    }

    // Task 1:
    public static <T> Collection<T> getElements(List<T> list, int... elements) {
        Collection<T> collection = new ArrayList<>();
        for (int element : elements) {
            if (element >= 0 && element < list.size()) {
                collection.add(list.get(element));
            }
        }
        return collection;
        // Task 2 - please see in Test
    }

    // Task 5 function implementation:
    public static int toWait() {
        int waitTime = new Random().nextInt(0, 1001);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return waitTime;
    }
}
