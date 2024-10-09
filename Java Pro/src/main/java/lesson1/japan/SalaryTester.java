package lesson1.japan;

public class SalaryTester {
    public static void main(String[] args) {
        JapaneseEmployee e1 = new JapaneseEmployee(
                "Mitsuo Basyo",
                150_000,
                10);
        JapaneseEmployee e2 = new JapaneseEmployee(
                "Akira Kurosava",
                200_000,
                2
        );

        // распечатайте зарплату обоих
        System.out.println(
                e1.getName() + " salary: " + e1.calculateSalary()
        );
        System.out.println(
                e2.getName() + " salary: " + e2.calculateSalary()
        );

        // JapaneseEmployee.coefficient = 23_000;
        // не работает так как поле final

    }
}
