package lesson29;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    // протестируйте умножение
    @Test
    public void testMultiply() {
        System.out.println("testMultiply");
        Calculator c = new Calculator();
        assertEquals(
                16,
                c.multiply(4, 4),
                0.001
        );
        assertEquals(
                0,
                c.multiply(4, 0),
                0.001
        );
        assertEquals(
                4,
                c.multiply(1, 4),
                0.001
        );
    }

    @Rule // специальное поле для перехвата исключений
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void divideByZero() {
        System.out.println("divideByZero");
        // ожидаемый класс исключения
        exception.expect(ArithmeticException.class);
        // ожидаемое сообщение
        exception.expectMessage("Division by zero");
        Calculator c = new Calculator();
        c.divide(10, 0);
    }

    @Before // выполняется перед каждой функцией с аннотацией @Test,
    // чтобы подготовить среду к тесту
    public void init() {
        System.out.println("init");
    }

    @After // выполняется после каждого теста
    // например, для удаления временных файлов, созданных программой при запуске тестов
    public void tearDown() {
        System.out.println("tearDown");
    }

    @BeforeClass // выполняется до первого теста - должны быть static
    public static void beforeAll() {
        System.out.println("beforeAll");
    }


    @AfterClass // выполняется после последнего теста - должны быть static
    public static void afterAll() {
        System.out.println("afterAll");
    }

}
