package lesson3.animals;

public class Cow  implements Animal {
    @Override
    public void move() {
        System.out.println("Cow is moving");
    }

    @Override
    public void voice() {
        System.out.println("Muuuuuuuuu!");
    }
}
