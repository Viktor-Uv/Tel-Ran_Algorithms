package lesson1;

import lesson1.japan.JapaneseEmployee;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JapaneseSalaryTest {
    // All functions MUST be public
    @Test // marks method as test
    public void testSalary() {
        JapaneseEmployee e = new JapaneseEmployee("Yukio", 100_000, 5);
        assertEquals(
                "Testing Salary",
                350_000, // что должно быть
                e.calculateSalary(), // вызов тестируемого метода
                0.001 // погрешность для double, так как double это не точный тип
        );
    }

    // напишите проверки для 10 и 3 летнего работников
    @Test
    public void testGreetings() {
        JapaneseEmployee e1 = new JapaneseEmployee("Akira", 150_000, 3);
        JapaneseEmployee e2 = new JapaneseEmployee("Takeshi", 200_000, 10);
        assertEquals(
                "Testing Greetings 3",
                "Hello, " + e1.getName(),
                e1.greetings()
        );
        assertEquals(
                "Testing Greetings 10",
                "Good day, " + e2.getName(),
                e2.greetings()
                );

    }
}
