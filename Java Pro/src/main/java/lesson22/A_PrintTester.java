package lesson22; // Date 06.09.2023

import java.util.Date;

/*
 * https://i0.wp.com/javaconceptoftheday.com/wp-content/uploads/2022/04/ByteStreamVsCharacterStreamInJava.png
 */

public class A_PrintTester {
    public static void main(String[] args) {
        // System.out - поток вывода
        // System.in - поток ввода
        // System.err - вывод ошибок

        System.out.print("one ");
        System.out.print("two ");
        System.out.println("three ");


        //                 формат boolean   целых        плавающая точка
        System.out.printf("It's a %b, years %d, salary is %f, name is %s\n", false, 15, 320.345, "Max");

        // три знака после запятой
        System.out.printf("pi is %.3f\n", Math.PI); // pi is 3,142

        // с шириной поля
        System.out.printf("pi is %10.3f\n", Math.PI); // pi is      3,142

        Date date = new Date();
        System.out.printf("hours: %1$tH, minutes: %1$tM, seconds: %1$tS \n", date); // hours: 20, minutes: 23, seconds: 53

    }
}
