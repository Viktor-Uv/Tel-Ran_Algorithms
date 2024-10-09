package lesson30;

// Date хранит время с точностью до миллисекунды
// хранит время в миллисекундах с эпохи
// 1 января 1970 - неофициальный др юникса. 16-bit processor, 2^16 max

import java.util.Date;
import java.util.Locale;

public class A_DateTester {
    // + когда требуется использовать дату в миллисекундах
    // + когда требуется сравнивать даты
    // + когда требуется использовать форматирование и локали

    // - нельзя работать с временными зонами
    // - нельзя делать математические операции с часами/днями/месяцами, и т.п.

    public static void main(String[] args) {
        Date current = new Date(); // текущая дата и время, Comparable
        System.out.println(current); // Mon Oct 02 21:13:15 CEST 2023
        System.out.println(current.getTime()); // 1696274066312 - миллисекунды с эпохи

        Date d = new Date(1696274066312L);
        System.out.println(d);

        Date d1 = new Date(1974, 0, 1); // месяцы в Date - с нуля
        System.out.println(d1.after(current)); // true

        System.out.println(new Date(0)); // Thu Jan 01 01:00:00 CET 1970
        System.out.println(new Date(70, 0, 1, 1, 0)); // Thu Jan 01 01:00:00 CET 1970

        // формат строки
        // https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax

        System.out.printf("%tY\n", current); // 2023
        System.out.printf("%tY-%tm-%td\n", current, current, current); // 2023-10-02

        Locale fr = new Locale("fr", "CA");
        System.out.printf(fr, "%tc", current); // lun. oct. 02 21:22:52 CEST 2023
    }
}
