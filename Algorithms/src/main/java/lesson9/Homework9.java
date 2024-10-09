package lesson9;

import lesson8.Homework8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Algorithms. Homework #9
 *
 * @author Viktor Uvarchev
 * @version 03 Sep 2023
 */

public class Homework9 {
    public static void main(String[] args) {
        // Task 1: Code Lock                Текущий набор        Секретная комбинация
        System.out.println(codeLock(new int[]{2, 3, 4, 5}, new int[]{5, 4, 3, 2})); // result: 8
        System.out.println(codeLock(new int[]{2, 3, 4, 5}, new int[]{1, 9, 1, 9})); // result: 12
        System.out.println(codeLock(new int[]{1, 9, 1, 9}, new int[]{0, 0, 0, 0})); // result: 4

        // Task 2: Dynamic Backpack
        int k = 4; //грузоподъёмность рюкзака
        //массив вещей
        Item[] items = {
                new Item("гитара", 1, 1500),
                new Item("бензопила", 4, 3000),
                new Item("ноутбук", 3, 2000)
        };

        // Решаем задачу о рюкзаке и выводим результат
        System.out.println("Оптимальный рюкзак: " + dynamicBackpack(items, k));
    }

    // Task 1: Code Lock
    static int codeLock(int[] dial, int[] unlock) {
        int rotations = 0;

        // For every ring
        for (int i = 0; i < dial.length; i++) {
            // Find absolute difference between dial and unlock rotations
            int absDifference = Math.abs(dial[i] - unlock[i]);
            // If difference is larger than the worst case (5), rotate backwards
            rotations += absDifference <= 5 ? absDifference : 10 - absDifference;
        }

        return rotations;
    }

    // Task 2: Dynamic Backpack
    public static Backpack dynamicBackpack(Item[] items, int k) {
        int n = items.length;
        //массив промежуточных состояний рюкзака
        Backpack[][] bp = new Backpack[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (i == 0 || j == 0) { //нулевую строку и столбец заполняем нулями
                    bp[i][j] = new Backpack(new Item[]{}, 0);
                } else if (i == 1) {
                    /*первая строка заполняется просто: первый предмет кладём или не кладём в зависимости от веса*/
                    bp[1][j] = items[0].getWeight() <= j ? new Backpack(new Item[]{items[0]}, items[0].getPrice())
                            : new Backpack(new Item[]{}, 0);
                } else {
                    if (items[i - 1].getWeight() > j) //если очередной предмет не влезает в рюкзак,
                        bp[i][j] = bp[i - 1][j];    //записываем предыдущий максимум
                    else {
                        /*рассчитаем цену очередного предмета + максимальную цену для (максимально возможный для рюкзака вес − вес предмета)*/
                        int newPrice = items[i - 1].getPrice() + bp[i - 1][j - items[i - 1].getWeight()].getPrice();
                        if (bp[i - 1][j].getPrice() > newPrice) //если предыдущий максимум больше
                            bp[i][j] = bp[i - 1][j]; //запишем его
                        else {
                            /*иначе фиксируем новый максимум: текущий предмет + стоимость свободного пространства*/
                            bp[i][j] = new Backpack(Stream.concat(Arrays.stream(new Item[]{items[i - 1]}),
                                    Arrays.stream(bp[i - 1][j - items[i - 1].getWeight()].getItems())).toArray(Item[]::new), newPrice);
                        }
                    }
                }
            }
        }

        // Вывод полученной в итоге таблицы:
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                System.out.print(bp[i][j] + " ");
            }
            System.out.print("\n");
        }

        // Перебираем все записи в последнем столбце и находим сочетание с наибольшей стоимостью.
        List<Backpack> lastColumn = Arrays.stream(bp)
                .map(row -> row[row.length - 1])
                .toList();

        return lastColumn.stream()
                .max(Comparator.comparing(Backpack::getPrice))
                .orElse(new Backpack(null, 0));
    }

    public static class Item {
        private final String name; //название вещи
        private final int weight; //вес
        private final int price; //стоимость

        public Item(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getPrice() {
            return price;
        }
    }

    public static class Backpack {
        private Item[] items;
        private int price;

        public Backpack(Item[] items, int price) {
            this.items = items;
            this.price = price;
        }

        public Item[] getItems() {
            return items;
        }

        public int getPrice() {
            return price;
        }

        //Описание состояния рюкзака
        @Override
        public String toString() {
            return items == null ? "" : Arrays.stream(items).map(Item::getName).collect(Collectors.joining("+")) + "-" + getPrice();
        }
    }

}
