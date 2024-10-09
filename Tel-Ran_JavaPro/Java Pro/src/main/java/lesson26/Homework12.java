package lesson26;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * JavaPro. Homework #12
 *
 * @author Viktor Uvarchev
 * @version 23 Sep 2023
 */

/*
 * Task 1.  Напишите функцию replaceAllVowels, удаляющую из строки все гласные
 *          в любом регистре и возвращающую измененную строку
 * Task 2.  Напишите функцию isPinCode, проверяющую, что строка является пин-кодом.
 *          Пин-код состоит из 4 или 6 цифр.
 * Task 3*. Напишите функцию mapAllIntsToDouble, которая бы добавляла
 *          к каждому целому в передаваемой строке ".0"
 *          Пример: "прибыль 12 расходы 20 доходы 50.3" -> "прибыль 12.0 расходы 20.0 доходы 50.3"
 * Task 4*. Посчитайте сумму цен растений из файла test.xml - $2.44+$9.37 должно быть 11.81
 */

public class Homework12 {
    public static void main(String[] args) throws Exception {
        // Task 1 test:
        System.out.println(
                replaceAllVowels(
                        "Напишите функцию replaceAllVowels, удаляющую из строки все гласные " +
                                "в любом регистре и возвращающую измененную строку"
                )
        ); // Output: Нпшт фнкц rplcllVwls, длщ з стрк вс глсн в лбм ргстр  взврщщ змннн стрк

        // Task 2 test:
        isPinCode("1234"); // Output: true
        isPinCode("12345"); // Output: false
        isPinCode("123456"); // Output: true
        isPinCode("12345a"); // Output: false

        // Task 3* test:
        mapAllIntsToDouble("прибыль 12 расходы 20 доходы 50.3");
        // Output: прибыль 12.0 расходы 20.0 доходы 50.3
        mapAllIntsToDouble("1 день прибыль 12 расходы 20 доходы 50.3 итого 9");
        // Output: 1.0 день прибыль 12.0 расходы 20.0 доходы 50.3 итого 9.0

        // Task 4* implementation:
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("./src/main/java/lesson26/files/test.xml"));
        Element root = doc.getDocumentElement();
        NodeList priceNodeList = root.getElementsByTagName("PRICE");
        double sum = 0;
        for (int i = 0; i < priceNodeList.getLength(); i++) {
            Node priceNode = priceNodeList.item(i);
            sum += Double.parseDouble(
                    priceNode.getFirstChild().getTextContent()
                            // strip String result from currency signs
                            .replaceAll("[^\\d.,]", "")
            );
        }
        System.out.printf("%.2f", sum); // Output: 11,81

    } // Main

    // Task 1 implementation:
    public static String replaceAllVowels(String s) {
        return s.replaceAll(
                "[АаЕеЁёИиОоУуыЭэЮюЯяAaEeIiOoUu]",
                ""
        );
    }

    // Task 2 implementation:
    public static void isPinCode(String s) {
        System.out.println(
                s.matches("\\d{4}|\\d{6}")
        );
    }

    // Task 3* implementation:
    public static void mapAllIntsToDouble(String s) {
        /*
        StringBuilder sb = new StringBuilder();
        for (String word : s.split("\\s")) {
            if (word.matches("\\d+")) {
                word += ".0"; // append ".0" to an integer-like string
            }
            sb.append(word).append(" "); // build a string with space at the end
        }
        sb.deleteCharAt(sb.length() - 1); // remove trailing space
        System.out.println(sb);
        */

        // Second solution 25.09.2023
        System.out.println(
                // ^ - символ начала строки
                // $ - символ конца строки
                // $1 / $2 / $3 - группы
                //                    $1     $2    $3
                s.replaceAll("(\\s|^)(\\d+)(\\s|$)", "$1$2.0$3")
        );
    }
}
