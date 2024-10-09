package lesson2.university;

public class Stuff extends Person {
    private double salary;

    public Stuff(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void introduce() {
        System.out.println(
                "I'm a Staff, Name: " + getName() +
                ", Salary: " + getSalary()
        );
    }
}
