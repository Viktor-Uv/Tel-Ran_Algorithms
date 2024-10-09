package lesson3.weekdays;

import java.util.Arrays;

public enum WeekDays {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    // напишите функцию
    public boolean isWeekDay()
    {
        // должна возвращать true для всех дней кроме субботы и воскресения
        return ordinal() < 5;
    }

    public static void main(String[] args) {
        System.out.println(MONDAY);
        System.out.println(SUNDAY.ordinal()); // index number while declaring
        System.out.println(
                Arrays.toString(
                        WeekDays.values() // array of values
                )
        );

        WeekDays f = WeekDays.FRIDAY;
        WeekDays w = WeekDays.valueOf("WEDNESDAY");
        System.out.println(w);
        // this element not exists
        // WeekDays w = WeekDays.valueOf("NOTSUCHDAY");
        System.out.println(f.isWeekDay()); // boolean
    }
}
