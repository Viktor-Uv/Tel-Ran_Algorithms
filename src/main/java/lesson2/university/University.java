package lesson2.university;

public class University {
    public static void main(String[] args) {
        // Declare from parent class, or
        Person p1 = new Student("Max", 21, 3, "politics");
        // declare from child class - doesn't matter
        Stuff s1 = new Stuff("Semen", 46, 50_000);

        // array of people
        Person[] people = {p1, s1};

        for (int i = 0; i < people.length; i++) {
            people[i].introduce();
        }
    }

}
