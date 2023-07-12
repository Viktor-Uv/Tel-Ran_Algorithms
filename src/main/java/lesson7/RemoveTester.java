package lesson7;

import lesson6.list.MyArrayList;
import lesson6.list.MyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RemoveTester {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        // remove all odd elements

//        так делать не надо, так как контейнер меняется не через итератор
//        при активном итераторе по осуществляется обход
//        for (Integer e : list)
//        {
//            if(e % 2 != 0)
//                list.remove(e);
//        }

//      так тоже не надо делать
//      IndexOutOfBoundsException
//      так как количество элементов меняется при удалении какого-нибудь из них
//        for (int i = 0; i < 6; i++) {
//            if (list.get(i) % 2 != 0) {
//                list.remove(i);
//            }
//        }

//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) % 2 != 0) {
//                list.remove(i);
//            }
//        }

        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            int element = iter.next();
            if (element % 2 != 0) {
                iter.remove();
            }
        }
        System.out.println(list);



        MyList myList = new MyArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        System.out.println(myList);

        // remove all odd elements

        Iterator<Integer> myListIterator = myList.iterator();
        while (myListIterator.hasNext()) {
            int element = myListIterator.next();
            if (element % 2 != 0) {
                myListIterator.remove();
            }
        }
        System.out.println(myList);

    }
}
