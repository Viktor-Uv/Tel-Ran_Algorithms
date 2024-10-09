package lesson30;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class B_CalendarTester {
    // + удобно делать математические операции с часами/днями/месяцами, и т.п.

    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance(); // текущая дата
        calendar.set(Calendar.DAY_OF_MONTH, 21); // установка (дня)
        calendar.add(Calendar.MONTH, -2); // уменьшение месяца на 2

        Date d = calendar.getTime(); // получение даты

        // SimpleDateFormat - для более удобного вывода или парсинга даты
        // https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(calendar.getTime())); // 2023-08-21 21:34:14

        Date n = sdf.parse("1999-11-07 12:43:11");
        System.out.println(n); // Sun Nov 07 12:43:11 CET 1999

        calendar.setTime(n);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // 1 - день недели у даты
    }
}
