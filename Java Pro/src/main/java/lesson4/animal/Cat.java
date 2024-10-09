package lesson4.animal;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void greets() { // override
        System.out.println("Meow");
    }
}
