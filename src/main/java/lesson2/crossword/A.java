package lesson2.crossword;

public class A {
    public void hello()
    {
        System.out.println("Hello from A");
    }

    // protected - visible only to child subclasses
    protected void world()
    {
        System.out.println("World from A");
    }

    final public void secure()
    {
        System.out.println("Secure from A");
    }
}
