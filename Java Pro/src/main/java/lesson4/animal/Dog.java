package lesson4.animal;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    @Override
    public void greets() { // override
        System.out.println("Woof");
    }
    public void greets(Dog dog) { // overload
        System.out.println("Woooof");
    }
}
