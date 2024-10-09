package lesson1.homework;

public class Person {
    private String fullName;
    private int age;

    // Constructors
    public Person() {
    }
    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    // Getters & Setters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // Methods
    public void move() {
        System.out.println("Person " + fullName + " walks");
    }
    public void talk() {
        System.out.println("Person " + fullName + " talks");
    }
}
