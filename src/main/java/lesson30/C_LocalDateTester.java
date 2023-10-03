package lesson30;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class C_LocalDateTester {
    // + имеет удобные методы

    public static void main(String[] args) {

        // LocalDate - this is only a Date, it doesn't have Time
        LocalDate now = LocalDate.now();
        System.out.println(now.getYear()); // 2023

        LocalDate nov10 = LocalDate.of(2005, Month.NOVEMBER, 10);
        System.out.println(nov10); // 2005-11-10

        nov10 = nov10
                .plusDays(5)
                .minusMonths(2);
        System.out.println(nov10); // 2005-09-15

        nov10 = nov10
                .withMonth(4) // set
                .with(ChronoField.DAY_OF_WEEK, 7);

        System.out.println(
                nov10.format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd") // 2005-04-14
                )
        );

        // LocalDateTime - has as well Time
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(
                ldt.format(
                        DateTimeFormatter.ofPattern("HH:mm:ss") // 21:45:06
                )
        );
    }
}
