package lesson1;

public class DogTester {
    public static void main(String[] args) {
        // оператор new использется для создания экземпляра класса
        String s = new String("Dima");
        Dog trezor = new Dog();
//        trezor.name = "Trezor";
//        trezor.breed = "овчарка";
//        trezor.age = 5;
        trezor.setName("Trezor");
        trezor.setBreed("овчарка");
        trezor.setAge(5);

        Dog eleonora = new Dog();
//        eleonora.name = "Eleonora";
//        eleonora.breed = "болонка";
//        eleonora.age = 7;
        eleonora.setName("Eleonora");
        eleonora.setBreed("болонка");
        eleonora.setAge(7);

        // create dog Max pitbull grey 8
        Dog max = new Dog("Pitbull", "Max", 8, "Grey");
        printDog(max);


        // вызвать printDog для элеоноры и для трезора
        printDog(eleonora);
        printDog(trezor);

        // bark
        eleonora.bark();
        trezor.bark();
        max.bark();

    }  // окончание main

    public static void printDog(Dog d)
    {
        System.out.println("Собака "
                + d.getName() + " породы "
                + d.getBreed() + " возраста "
                + d.getAge()
        );
    }
}