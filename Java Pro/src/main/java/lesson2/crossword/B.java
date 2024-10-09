package lesson2.crossword;

public class B extends A { // extends class "A"
    // A - super class (parent)
    // B - subclass (child)

    @Override // ensures that parent method is being overridden
    protected void world() {
        super.world(); // call method from parent class
        System.out.println("World from B");
    }

    // добавьте реализацию метода hello
    // чтобы печатала "Hello, Hello from B!!!"
    @Override
    public void hello() {
        System.out.println("Hello, Hello from B!!!");
    }

    // объявлен в родительском классе как final и не подлежит
    // переопределению
    //    public void secure() {
    //
    //    }
}
