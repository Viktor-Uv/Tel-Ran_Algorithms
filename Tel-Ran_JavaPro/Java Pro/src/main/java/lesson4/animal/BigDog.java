package lesson4.animal;

public class BigDog extends Dog {
    public BigDog(String name) {
        super(name);
    }
    @Override
    public void greets() { // override
        System.out.println("Wooow");
    }
    @Override
    public void greets(Dog d) { // override
        System.out.println("Woooooow");
    }
    // перегруженный метод не получится вызвать по ссылке на базовый класс
    public void greets(BigDog dog) { // overload
        System.out.println("Wooooooooow");
    }
}
