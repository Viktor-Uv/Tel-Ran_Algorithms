package lesson28.generic.list;

public class MyGenericListTester {
    public static void main(String[] args) {
        MyGenericArrayList<String> s = new MyGenericArrayList<>();
        s.add("one");
        s.add("two");
        s.add("three");
        s.add("four");
        s.add("five");

        System.out.println(s);
        System.out.println(s.contains("ten")); // false
        System.out.println(s.contains("five")); // true
        s.remove(0);
        System.out.println(s.size());
        System.out.println(s);
    }
}
