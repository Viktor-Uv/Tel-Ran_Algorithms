package lesson8;

import java.util.*;

/**
 * Algorithms Lesson #8. Dynamic Array
 * @version 17 Aug 2023
 */

/*
 * https://htmlacademy.ru/blog/js/greedy-algo
 */

public class Lesson8 {
    public static void main(String[] args) {
        // 1 задача - выбор задач
        List<Integer> tasks = Arrays.asList(3, 2, 1, 4, 2, 5, 1);
        int maxTime = 9;
        List<Integer> resolved = taskSelection(tasks, maxTime);
        System.out.println(resolved);

        // 2 задача - размен монет
        List<Integer> coins = Arrays.asList(1, 5, 10, 25);
        int amount = 63;
        System.out.println(coinChange(coins, amount));

    } // Main

    static List<Integer> coinChange(List<Integer> coins, int amount) {
        List<Integer> result = new ArrayList<>();

        Collections.sort(coins);
        for (int i = coins.size() - 1; i >= 0; i--) {
            while (amount >= coins.get(i)) {
                amount = amount - coins.get(i);
                result.add(coins.get(i));
            }
        }

        if (amount == 0) {
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    static List<Integer> taskSelection(List<Integer> tasks, int maxTime) {
        List<Integer> result = new ArrayList<>();
        int resultTime = 0;

        LinkedList<Integer> tasksList = new LinkedList<>(tasks);
        Collections.sort(tasksList);
        while (
                resultTime < maxTime &&
                        maxTime - resultTime >= tasksList.peek()
        ) {
            int taskTime = tasksList.pop();
            result.add(taskTime);
            resultTime += taskTime;
        }

        return result;
    }
}
