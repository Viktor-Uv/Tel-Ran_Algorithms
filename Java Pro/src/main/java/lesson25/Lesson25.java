package lesson25; // Date 18.09.2023

/*
Применения регулярных выражений / regex
1. проверка соответствия строки шаблону
2. разбиение строки по регулярному выражению
3. замена

https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 */

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lesson25 {
    public static void main(String[] args) {
        String string = "this is a max temperature and it has a real maximum";
        Pattern max = Pattern.compile("max"); // скомпилированный шаблон
        Matcher maxMatcher = max.matcher(string); // все совпадения с шаблоном в строке
        while (maxMatcher.find()) {
            System.out.printf(
                    "s: %d, e:%d, group: %s\n",
                    maxMatcher.start(), // порядковый номер начального символа совпадения
                    maxMatcher.end(), // порядковый номер конечного символа совпадения
                    maxMatcher.group() // само совпадение
            );
        }
        System.out.println("=== .* ===");
        // . - любой символ
        // * - 0 и более предыдущих символов
        String telran = "https://www.telran.de";
        Pattern telranPattern = Pattern.compile(".*www.*", Pattern.CASE_INSENSITIVE);
        System.out.println(
                telranPattern.matcher(telran).matches() // есть ли совпадения?
        );
        // если нужно проверить наличие точки, то ее нужно "искейпить" - \\.
        // "\\"
        System.out.println(
                // telran.contains("www\\.") // \\ - обратный слэш тоже нужно искейпить
                telran.matches(".*www\\..*")
        );

        // hello -> hel.*
        System.out.println("hello".matches("hel.*"));
        // hello -> hel..
        System.out.println("hello".matches("hel.."));

        // * - нуль и более предыдущих символов
        // + - один и более предыдущих символов
        // ? - нуль или один предыдущий символ
        // {1,7} - от одного до 7 предыдущих символов
        // {2,} - от двух и более
        // {,6} - до шести
        System.out.println("=== | - альтернатива ==="); // | - либо
        System.out.println("max".matches("dima|max|masha")); // true
        System.out.println("maxmasha".matches("dima|max|masha")); // false

        // поправьте шаблон
        System.out.println("=== () - группировка ===");
        System.out.println("maxmasha".matches(".*(dima|max|masha).*")); // true

        System.out.println("=== [] - перечисление ===");
        // любой символ из перечисления
        System.out.println("a".matches("[abcdefx]")); // true

        System.out.println("abc".matches("[abcdefx]*")); // true
        System.out.println("abc".matches("[abcdefx]{3}")); // true
        System.out.println("aba".matches("[abcdefx]{2}.")); // true
        // диапазон
        System.out.println("abc".matches("[a-fx]{3}")); // true

        System.out.println("=== [^] - отрицание ===");
        System.out.println("a".matches("[^klm]")); // true

        System.out.println("=== \\s - пробельные символы ==="); // \s
        // пробельные символы - это \n \t " "

        // напишите шаблон
        // одна и более букв + один и более пробельных символов + одна и более букв
        System.out.println("max   kotkov".matches("[a-z]+\\s+[a-z]+")); // true

        System.out.println("Max   Kotkov".matches("[A-Z][a-z]+\\s+[A-Z][a-z]+")); // true

        System.out.println("abc123".matches("[a-z]{3}[0-9]{3}")); // true
        System.out.println("abc123".matches("[a-z]{3} [0-9]{3}")); // false

        System.out.println("=== hex number ===");
        System.out.println(isHexNumber("0x1")); // true
        System.out.println(isHexNumber("123")); // false
        System.out.println(isHexNumber("0XB")); // true
        System.out.println(isHexNumber("abc")); // false
        System.out.println(isHexNumber("0x")); // false
        System.out.println(isHexNumber("0xABW")); // false

        System.out.println("=== phone number ===");
        // + нужно искейпить
        System.out.println(isPhoneNumber("+1")); // true
        System.out.println(isPhoneNumber("1")); // false
        System.out.println(isPhoneNumber("+1A")); // false
        System.out.println(isPhoneNumber("123")); // false

        System.out.println("=== ipv4 address ===");
        System.out.println(isIpV4Address("0.0.0.0")); // true
        System.out.println(isIpV4Address("10.0.0.0")); // true
        System.out.println(isIpV4Address("00.0.0.0")); // false
        System.out.println(isIpV4Address("0.0.0.0.")); // false
        System.out.println(isIpV4Address("0.0.0")); // false
        System.out.println(isIpV4Address("255.255.255.300")); // false
        System.out.println(isIpV4Address("255.255.a.255")); // false
        System.out.println(isIpV4Address("255.255.255.255")); // true

        // \\s - пробельные символы
        // \\w - символ слова
        // \\d - цифры


        System.out.println("=== разбиение ===");
        String line = "to be     or  not        to      be";
        // разбейте на слова
        // "".split("")
        System.out.println(
                Arrays.toString(
                        line.split("\\s+")
                )
        );

        System.out.println(getExtension("hello.png")); // png
        System.out.println(getExtension("my.resume.1.doc")); // doc
        System.out.println(getExtension("hello")); // null


        System.out.println("=== () группировка ===");
        String people = "Max Semenov     Lena Petrova  Igor Semin";
        //                                                  .group(1) .group(2)
        Pattern firstLastNamePattern = Pattern.compile("(\\w+)\\s(\\w+)");
        Matcher namesMatcher = firstLastNamePattern.matcher(people);
        while (namesMatcher.find()) {
            System.out.printf("first: %s, last: %s\n", namesMatcher.group(1), namesMatcher.group(2));
        }

        // разбейте на части по ';' и по '|' и распечатайте составные части
        String employeeData = "michael;levanov|34|34000";
        System.out.println(
                Arrays.toString(
                        employeeData.split("[;|]")
                )
        );

        System.out.println("=== замена ===");
        String temperature = "Today's temperature is 18 degrees centigrade";
        temperature = temperature
                .replaceAll("\\d+", "45")
                .replaceAll("centi", "milli");
        System.out.println(temperature);

        String numbers = "one two three four zero";
        numbers = numbers
                .replaceAll("(t\\w+)", "_&1_");
        System.out.println(numbers);

    } // Main

    // функция должна возвращать расширение файла
    // hello.png -> png
    // my.resume.1.doc -> doc
    public static String getExtension(String fileName)
    {
        String[] parts = fileName.split("\\.");
        if (parts.length == 1) {
            return null;
        } else {
            return parts[parts.length - 1];
        }
    }

    // четыре цифры разделенные точками
    // 1.2.3.4
    // каждая цифра 0-255
    public static boolean isIpV4Address(String string) {
        //               0-9  10-99   100-199 200-249    250-255
        String octet = "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d||25[0-5])";
        String subPattern = "(" + octet + "\\.){3}";
        return string.matches(subPattern + octet);
    }

    // напишите функцию, которая проверяет, является ли переданная
    // в нее строка номером телефона
    // номер телефона состоит из + и одной или более цифр
    public static boolean isPhoneNumber(String string) {
        return string.matches("\\+[0-9]+");
    }

    // напишите функцию, которая проверяет, является ли переданная
    // в нее строка шестнадцатеричным номером
    // 0x12AB
    // 0XB
    // 0-9, a-f, A-F
    public static boolean isHexNumber(String string) {
        return string.matches("0[xX][0-9a-fA-F]+");
    }
}
