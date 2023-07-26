package lesson8;

public class MyLinkedListTester {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(12);
        list.add(5);
        System.out.println(list + " size: " + list.size());

        System.out.println("\nContains 4?: " + list.contains(4));
        System.out.println("Contains 1?: " + list.contains(1));

        System.out.println("\n" + list.get(1));

        list.add(1, 22); // insert
        list.add(4, -2); // add to the end
        System.out.println("\n" + list + " size: " + list.size());

        list.remove(3);
        list.remove(1);
        System.out.println("\n" + list + " size: " + list.size());

        list.addFirst(4);
        System.out.println("\n" + list.getFirst());
        System.out.println(list + " size: " + list.size());

        System.out.println("\nRemove: " + list.removeFirst());
        System.out.println(list + " size: " + list.size());
    }
}
