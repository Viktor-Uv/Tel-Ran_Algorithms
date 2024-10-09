package lesson44;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
    Transaction - транзакция - группа из нескольких sql запросов,
    результаты выполнения которой либо должны быть отражены в базе данных полностью,
    либо, в случае ошибки, должны быть отброшены результаты промежуточных запросов

    commit - транзакция выполнилась успешно и ее нужно зафиксировать в базы данных
    rollback - что-то пошло не так, все промежуточные изменения нужно отбросить

    Transaction used for DML: UPDATE, INSERT, DELETE

    DDL - data definition language - create table ... , alter table ...
    DML - data manipulation language - select, insert, update, delete
 */

public class G_Transaction {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:shop.db";

        try (
                Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
        ) {
            // autocommit=true - каждый запрос выполняется в рамках отдельной транзакции
            // и выполнять commit не требуется

            // autocommit=false - все запросы выполняются в рамках одной транзакции
            // которую нужно подтвердить вызвав commit
            conn.setAutoCommit(false);

/*
        create table customers (cnum int primary key not null, cname text not null, city
                    text not null, rating integer not null, snum int not null);

        create table orders (onum int primary key not null, amt int not null, odate text
                    not null, cnum int not null, snum int not null);
 */

            // добавить кастомера 4001, 'Ferguson', 'Berlin', 100, 1001
//            stmt.execute("INSERT INTO customers VALUES (4001, 'Ferguson', 'Berlin', 100, 1001);");

            // добавить заказ 7001, 150000, '2023-10-25', 4001, 1001
//            stmt.execute("INSERT INTO orders VALUES (7001, 150000, '2023-10-25', 4001, 1001);");

            stmt.execute("INSERT INTO customers VALUES (4002, 'Below', 'Koln', 100, 1001);");

            System.out.println(4 / 0); // cause Exception
            // conn.rollback(); // если хотим отбросить

            stmt.execute("INSERT INTO orders VALUES (7002, 250000, '2023-10-20', 4002, 1001);");

            conn.commit();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
