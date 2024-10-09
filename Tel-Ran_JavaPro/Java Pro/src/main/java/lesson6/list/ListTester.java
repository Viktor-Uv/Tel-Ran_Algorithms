package lesson6.list;

public class ListTester {
    public static void main(String[] args) {
        MyArrayList test = new MyArrayList();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        System.out.println(test);
        System.out.println(test.get(4));
        test.remove(1);
        System.out.println("After rem: " + test);
        System.out.println(test.size());
        test.add(2, 9);
        test.add(1, 2);
        System.out.println("After add: " + test);
        System.out.println(test.size());
    }
}
