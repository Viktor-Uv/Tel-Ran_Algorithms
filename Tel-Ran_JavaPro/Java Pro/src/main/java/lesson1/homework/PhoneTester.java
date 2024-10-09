package lesson1.homework;

public class PhoneTester {
    public static void main(String[] args) {
        Phone p1 = new Phone("+123 (456) 789", "myPhone", 100);
        Phone p2 = new Phone("+1 (234) 658 97", "yourPhone", 95);
        Phone p3 = new Phone("+12 (385) 6974", "theirPhone", 105);

        p1.printValues();
        p2.printValues();
        p3.printValues();
        System.out.println(); // New line

        p1.receiveCall("Harry Potter");
        System.out.println("Number: " + p1.getNumber());
        p2.receiveCall("Hermione Granger");
        System.out.println("Number: " + p2.getNumber());
        p3.receiveCall("Ron Weasley");
        System.out.println("Number: " + p3.getNumber());
    }
}
