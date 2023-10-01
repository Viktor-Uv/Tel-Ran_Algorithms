package lesson28.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProducerConsumer {
    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>(Arrays.asList(3.15, 3));
        numberList.add(13.33); // ok так как Double является производным от Number
        numberList.add(-1); // ok так как Integer является производным от Number

        // Double d = numberList.get(1); // not ok - так как в numberList хранятся
        //                               Number (когда printListOfNumbers(List<Number> numberList))
//         printListOfNumbers(numberList); // ok (когда printListOfNumbers(List<Number> numberList))

        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3));
//        printListOfNumbers(integerList); // not ok - нарушение условий
//                                          контракта (когда printListOfNumbers(List<Number> numberList))
        Number n = integerList.get(0);
        printListOfNumbers(integerList);


    } // Main

    // Consumer
    // контракт <? extends Number> - мы обязуемся только считывать оттуда Number,
    // но не записывать его
    public static void printListOfNumbers(List<? extends Number> numberList) {
        numberList.forEach(
                n -> System.out.println("number is: " + n)
        );
//        numberList.add(33); // not ok - нарушение контракта - обязались ничего не записывать
        // в numberList разрешено записывать Double, Integer, Long, и т.д.
    }

    // Producer
    // контракт <? super Integer> - мы только записываем в контейнер,
    // и обязуемся ничего не считывать
    public static void addNumber(List<? super Integer> integerList) {
        integerList.add(
                new Random().nextInt()
        );
//        Integer i = integerList.get(0); // not ok - нарушение контракта - обязались ничего не считывать
        // в integerList могут находиться и Number, и Object
    }

}
