
package lesson8;

import java.util.*;

/**
 * Algorithms. Homework #8
 *
 * @author Viktor Uvarchev
 * @version 02 Sep 2023
 */

public class Homework8 {
    public static void main(String[] args) {
        // 1 задача - выбор максимального количества задач
        //            за ограниченное время
        List<Integer> tasks = Arrays.asList(3, 2, 1, 4, 2, 5, 1);
        int maxTime = 9;
        System.out.println(taskSelection(tasks, maxTime));

        // 2 задача - размен минимальным количеством монет
        List<Integer> coins = Arrays.asList(1, 5, 10, 25);
        int amount = 63;
        System.out.println(coinChange(coins, amount));

        // 3 задача - уложить максимальную стоимость предметов в рюкзак,
        //            не превышая его вместимость
        List<Item> items = Arrays.asList(
                new Item("A",7, 100),
                new Item("B",5, 80),
                new Item("C",3, 50)
        );
        double capacity = 10;
        System.out.println(packByUnitCost(items, capacity));

    } // main

    public static class Item {
        private final String name;
        private final double weight;
        private final double value;

        public Item(String name, double weight, double value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public double getWeight() {
            return weight;
        }

        public double getValue() {
            return value;
        }

        public double getUnitCost() {
            return getValue() / getWeight();
        }

        @Override
        public String toString() {
            return "I{" +
                    "n='" + name + '\'' +
                    ", w=" + weight +
                    ", v=" + value +
                    '}';
        }
    }

    // 1 задача
    static List<Integer> taskSelection(List<Integer> tasks, int maxTime) {
        // Convert array list to linked list, and sort linked list
        LinkedList<Integer> taskList = new LinkedList<>(tasks);
        Collections.sort(taskList);

        // Create result array
        List<Integer> result = new LinkedList<>();

        // select tasks until limit reached or all tasks selected
        while (taskList.peek() != null && maxTime >= taskList.peek()) {
            maxTime -= taskList.peek();
            result.add(taskList.pop());
        }

        return result;
    }

    // 2 задача
    static List<Integer> coinChange(List<Integer> coins, int amount) {
        // Reverse-sort coins list
        coins.sort((i1, i2) -> Integer.compare(i2, i1));

        // Create result array
        List<Integer> result = new LinkedList<>();

        // Deduct coins and add to result list until amount is 0
        for (int i = 0; i < coins.size() && amount > 0; i++) {
            while (amount >= coins.get(i)) {
                amount -= coins.get(i);
                result.add(coins.get(i));
            }
        }

        // Check if change is successful
        if (amount == 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    // 3 задача
    static List<Item> packByUnitCost(List<Item> items, double capacity) {
        List<Item> packedItems = new ArrayList<>();

        // Сортировка предметов по убыванию их удельной стоимости (стоимость / вес)
        items.sort(
                Comparator.comparingDouble(Item::getUnitCost).reversed()
        );

        // Перебор предметов по одному, начиная с более ценных
        for (Item item : items) {
            if (item.getWeight() <= capacity) {
                // Если для вещи ещё есть место в рюкзаке - положим её
                packedItems.add(item);
                // Обновим оставшуюся вместимость
                capacity -= item.getWeight();
            } else {
                break;
            }
        }

        return packedItems;
    }

}
