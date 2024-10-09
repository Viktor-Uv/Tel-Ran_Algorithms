package lesson16;

import java.util.*;

/**
 * JavaPro. Homework #7
 *
 * @author Viktor Uvarchev
 * @version 27 Aug 2023
 */

/*
 * Task 1. Напишите функцию, которая вернет самый часто встречающийся элемент в списке
 *
 * Task 2. Напишите функцию, которая вернет все элементы списка,
 *         которые встретились четное число раз
 *
 * Task *3. Есть класс автомобиль с полями марка модель цена
 *         Написать метод, который принимает список авто и возвращает TreeMap,
 *         в котором ключами являются марки автомобилей, а значениями будут списки автомобилей,
 *         отсортированные относительно цены и модели (дешевые вперед)
 *
 * Task *4. Сгруппируйте слова с одинаковым набором символов
 *         Дан список слов со строчными буквами. Реализуйте функцию поиска всех слов
 *         с одинаковым уникальным набором символов.
 *
 *         вход: String words[] = {"student", "students", "dog", "god", "cat", "act", "flow", "wolf"};
 *         выход:
 *         [student, students],
 *         [cat, act],
 *         [dog, god],
 *         [flow, wolf]
 */

public class Homework7 {
    public static void main(String[] args) {
        // Task 1 test:
        List<String> task1 = new ArrayList<>(
                Arrays.asList(
                        "Напишите функцию, которая вернет самый часто встречающийся элемент в списке"
                                .split("")
                )
        );
        System.out.println("Task 1: " + mostFrequentElement(task1) + "\n"); // { =9}

        // Task 2 test:
        List<String> task2 = new ArrayList<>(
                Arrays.asList(
                        ("Напишите функцию, которая вернет все элементы списка," +
                                "которые встретились четное число раз")
                                .split("")
                )
        );
        System.out.println("Task 2: " + evenFrequencyElements(task2) + "\n"); // { =10, а=4, с=6, т=8, е=10, ч=2, к=4, ы=2, ,=2, н=4, о=6, п=2}

        // Task 3 test:
        List<Car> carList = new ArrayList<>(Arrays.asList(
                new Car("Chevrolet", "Venture", 52264),
                new Car("Honda", "CR-V", 75629),
                new Car("Ford", "Econoline E350", 32099),
                new Car("Mitsubishi", "Truck", 25870),
                new Car("Volkswagen", "Beetle", 78408),
                new Car("Ford", "Econoline E250", 31016),
                new Car("Land Rover", "Discovery", 32099),
                new Car("Volkswagen", "Eos", 63251),
                new Car("Suzuki", "Swift", 25870),
                new Car("Ford", "Econoline E150", 31016)
        ));
        System.out.println("Task 3: " + sortPriceModel(carList) + "\n"); // {Volkswagen=[{b='Volkswagen',m='Eos',p=63251}, {b='Volkswagen',m='Beetle',p=78408}], Chevrolet=[{b='Chevrolet',m='Venture',p=52264}], Suzuki=[{b='Suzuki',m='Swift',p=25870}], Mitsubishi=[{b='Mitsubishi',m='Truck',p=25870}], Ford=[{b='Ford',m='Econoline E150',p=31016}, {b='Ford',m='Econoline E250',p=31016}, {b='Ford',m='Econoline E350',p=32099}], Honda=[{b='Honda',m='CR-V',p=75629}], Land Rover=[{b='Land Rover',m='Discovery',p=32099}]}

        // Task 4 test:
        String[] words = {"student", "students", "dog", "god", "cat", "act", "flow", "wolf"};
        System.out.println("Task 4: " + uniqueSymbolWords(words)); // {[s, t, d, u, e, n]=[student, students], [a, c, t]=[act, cat], [f, w, l, o]=[wolf, flow], [d, g, o]=[dog, god]}

    } // Main

    // Task 1 implementation:
    public static Map<String, Integer> mostFrequentElement(List<String> list) {
        // Create Map from list
        Map<String, Integer> map = new HashMap<>();
        for (String element : list) {
            if (map.get(element) == null) {
                map.put(element, 1);
            } else {
                map.put(element, map.get(element) + 1);
            }
        }

        // Find maximum frequency using Map.Entry and comparator
        List<Map.Entry<String, Integer>> mapEntryList = new ArrayList<>(map.entrySet());
        String maxElement = Collections.max(
                mapEntryList,
                (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())
        ).getKey();

        // Return new map with one element
        return Map.of(maxElement, map.get(maxElement));
    }

    // Task 2 implementation:
    public static Map<String, Integer> evenFrequencyElements(List<String> list) {
        // Create Map from list
        Map<String, Integer> map = new HashMap<>();
        for (String element : list) {
            if (map.get(element) == null) {
                map.put(element, 1);
            } else {
                map.put(element, map.get(element) + 1);
            }
        }

        // Create new map and fill only with even values using Map.Entry
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    // Task 3 implementation:
    public static class Car {
        private final String brand;
        private final String model;
        private final int price;

        public Car(String brand, String model, int price) {
            this.brand = brand;
            this.model = model;
            this.price = price;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "{" +
                    "b='" + brand + '\'' +
                    ",m='" + model + '\'' +
                    ",p=" + price +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Car car = (Car) o;

            if (price != car.price) return false;
            if (!Objects.equals(brand, car.brand)) return false;
            return Objects.equals(model, car.model);
        }

        @Override
        public int hashCode() {
            int result = brand != null ? brand.hashCode() : 0;
            result = 31 * result + (model != null ? model.hashCode() : 0);
            result = 31 * result + price;
            return result;
        }
    }
    // Task 3 implementation (continuation):
    public static Map<String, TreeSet<Car>> sortPriceModel(List<Car> cars) {
        // Create new comparator according to the task, and empty Map
        Comparator<Car> priceModelComparator =
                Comparator.comparing(Car::getPrice)
                .thenComparing(Car::getModel);
        Map<String, TreeSet<Car>> result = new HashMap<>();

        // In every given car from the input list
        for (Car car : cars) {
            // Get record of created brand-specific list of car, or make new empty one
            TreeSet<Car> specificBrand = result.get(car.getBrand());
            if (specificBrand == null) {
                specificBrand = new TreeSet<>(priceModelComparator);
            }
            // Add brand-specific car to the list and updated list to the result Map
            specificBrand.add(car);
            result.put(car.getBrand(), specificBrand);
        }

        return result;
    }

    // Task 4 implementation:
    public static Map<Set<String>, Set<String>> uniqueSymbolWords(String[] words) {
        // Create Map for key: set of symbols = value: set of words
        Map<Set<String>, Set<String>> result = new HashMap<>();

        // For every given word
        for (String word : words) {
            // Split it on unique set of symbols
            Set<String> setOfSymbols = new HashSet<>(Arrays.asList(
                    word.split("")
            ));
            // Get record of created set of words with these symbols, or make new empty one
            Set<String> setOfWords = result.get(setOfSymbols);
            if (setOfWords == null) {
                setOfWords = new HashSet<>();
            }
            // Add set-specific word to the set and updated set to the result Map
            setOfWords.add(word);
            result.put(setOfSymbols, setOfWords);
        }

        return result;
    }
}
