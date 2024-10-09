package lesson2.crossword;

// создайте класс C от класса B и переопределите метод
// hello
// чтобы он печатал "Hello, I'm a C!!!"
public class C extends B {
    @Override
    public void hello() {
        super.hello();
        System.out.println("Hello, I'm a C!!!");
    }
}
