package lesson39.annotation;

public class Child extends Parent {
    //    public void disPlay() { // this is error in a function name. @Override is there to help avoid it
    @Override
    public void display() {
        System.out.println("Child class display");
    }
}
