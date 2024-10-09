package lesson44.homework20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JavaPro. Homework #20
 *
 * @author Viktor Uvarchev
 * @version 24 Oct 2023
 */

/*
 * Task:
 * Распечатайте все заказы определенного покупателя выше определенной суммы.
 * И покупатель и сумма должны передаваться в виде параметров в параметризованный запрос.
 */

public class OrderFilter {
    public static void main(String[] args) {
        printFilteredOrders("Grass", 10_000);
        printFilteredOrders("Grass", 7_000);
        printFilteredOrders("Giovanni", 100_000);
    }

    public static void printFilteredOrders(String customerName, int minAmount) {
        String url = "jdbc:sqlite:shop.db";

        try (
                Connection conn = DriverManager.getConnection(url);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * " +
                                " FROM orders " +
                                " WHERE cnum IN ( " +
                                "     SELECT cnum " +
                                "     FROM customers " +
                                "     WHERE cname = ? ) " +
                                " AND amt > ?;"
                );
        ) {
            statement.setString(1, customerName);
            statement.setInt(2, minAmount);
            try (
                    ResultSet result = statement.executeQuery();
            ) {
                // Print header
                System.out.println("+-----+-------+-----------+-----+-----+");
                System.out.printf("|%5s|%7s|%11s|%5s|%5s|\n",
                        "onum", "amt", "odate", "cnum", "snum"
                );
                System.out.println("+-----+-------+-----------+-----+-----+");

                // Print selected data
                int rowsCounter = 0;
                while (result.next()) {
                    int onum = result.getInt("onum");
                    int amt = result.getInt("amt");
                    String odate = result.getString("odate");
                    int cnum = result.getInt("cnum");
                    int snum = result.getInt("snum");

                    System.out.printf("|%5d|%7d|%11s|%5d|%5d|\n",
                            onum, amt, odate, cnum, snum
                    );
                    rowsCounter++;
                }
                // Output number of returned rows and a separator line after the table
                System.out.println("+-----+-------+-----------+-----+-----+");
                System.out.println("Rows returned: " + rowsCounter + "\n");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
