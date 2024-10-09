package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        // List<Integer> l = Arrays.asList(1,2,3,4,5,6,7) - READ ONLY
        System.out.println(l);
        removeAllGreater(l, 4);
        System.out.println(l);

        List<String> strings = new ArrayList<>(Arrays.asList(
                "hello", "max", "world", "is", "world", "max", "masha", "world"
        ));
        removeDuplicates(strings);
        System.out.println(strings);

    }

    // напишите функцию удаляющую из списка строк дубликаты
    public static void removeDuplicates(List<String> s) {
        Iterator<String> iterator = s.iterator();
        List<String> seenAlready = new ArrayList<>();

        while (iterator.hasNext()) {
            String element = iterator.next();
            if (seenAlready.contains(element)) {
                iterator.remove();
            }
            seenAlready.add(element);
        }
    }

    // напишите функцию которая удалит из списка целых
    // все целые больше заданого
    public static void removeAllGreater(List<Integer> l, int value) {
        Iterator<Integer> iterator = l.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() > value) {
                iterator.remove();
            }
        }

//   не работает!!!

//        for(int i : l)
//        {
//            if(i > value)
//                l.removeAll(Arrays.asList(i));
//        }

//        List<Integer> toRemove = new ArrayList<>();
//        for(int i: l)
//        {
//            if(i > value)
//                toRemove.add(i);
//        }
//        l.removeAll(toRemove);
    }




}
