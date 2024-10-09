package lesson44;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class B_CreateTables {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:shop.db";

        try (
                Connection conn = DriverManager.getConnection(url);
                // Request:
                Statement stmt = conn.createStatement();
        ) {
            // stmt.execute() выполнение произвольных запросов
            // stmt.executeUpdate() выполнение update/delete - возвращает количество
            //      измененных строк в таблице
            // stmt.executeQuery() select - возвращает результат

            stmt.execute("create table salespeople (snum int primary key not null, " +
                    " sname text not null, city " +
                    " text not null, comm integer not null);");
            System.out.println("Table salespeople created!");

            // добавьте код для создания покупателей и заказов
            stmt.execute("create table customers (cnum int primary key not null, " +
                    " cname text not null, city " +
                    " text not null, rating integer not null, snum int not null);");
            System.out.println("Table customers created!");

            stmt.execute("create table orders (onum int primary key not null, " +
                    " amt int not null, odate text " +
                    " not null, cnum int not null, snum int not null);");
            System.out.println("Table orders created!");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
