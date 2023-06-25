package lesson1.homework;

public class PersonTester {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person("Tom Hanks", 66);

        p1.setFullName("Dwayne The Rock Johnson");
        p1.setAge(51);

        p1.move();
        p2.talk();
    }
}
