package lesson3.animals;

public class Cat implements Animal {
    @Override
    public void move() {
        System.out.println("Cat is jumping");
    }

    @Override
    public void voice() {
        System.out.println("Meuuuuuu!");
    }
}
