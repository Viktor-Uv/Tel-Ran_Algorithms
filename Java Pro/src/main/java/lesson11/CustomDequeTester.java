package lesson11;

public class CustomDequeTester {
    public static void main(String[] args) {
        CustomDeque c = new CustomDequeImpl();
        c.addFirst(3);
        c.addFirst(2);
        c.addFirst(1);
        System.out.println(c); // [1, 2, 3]
        c.addFirst(0);
        c.addFirst(-1);
        System.out.println(c); // [-1, 0, 1, 2, 3]
        System.out.println(c.getFirst()); // -1
//        System.out.println(new CustomDequeImpl().getFirst());
        System.out.println(c.removeFirst()); // -1
        System.out.println(c.getFirst()); // 0
        System.out.println(c.getLast()); // 3
        c.addLast(4);
        System.out.println(c); // [0, 1, 2, 3, 4]
        System.out.println(c.removeLast()); // 4
        System.out.println(c); // [0, 1, 2, 3]
    }
}
