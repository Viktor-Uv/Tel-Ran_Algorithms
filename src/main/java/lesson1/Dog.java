package lesson1;

// class - similar characteristics
// class - template for its children
// class - set of properties and actions
// class instance - class child

public class Dog {
    // private - property is accessible only inside class
    private String breed; // порода
    private String name; // имя собаки
    private int age; // возраст
    private String color; // цвет


    // constructor without parameters
    public Dog(){
    }

    // constructor with parameters


    public Dog(String breed, String name, int age, String color) {
        this.breed = breed;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 50) {
            this.age = age;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void bark()
    {
        // внутри класса доступ есть ко всем полям
        // независимо от уровня доступа - private, public etc
        System.out.println(name + " bark, bark!");
    }

}
