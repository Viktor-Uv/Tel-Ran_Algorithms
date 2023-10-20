package lesson44;

// https://sqlite.org/index.html

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class A_CreateDatabase {
    // JDBC - Java Database Connectivity
    // url - ip адрес сервера, порт, название базы данных, логин/пароль и пт
    // url - universal resource locator - идентификатор ресурса
    // http://www.google.com
    // tel:123
    // jdbc:oracle:thin:@localhost:1521:xe
    public static void main(String[] args) {
        String url = "jdbc:sqlite:shop.db";

        try (
                // Connection реализует интерфейс AutoClosable
                // и поэтому для него автоматически будет
                // вызван close при выходе из try-блока
                Connection conn = DriverManager.getConnection(url);
        ) {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("The driver is: " + metaData.getDriverName());
            System.out.println("Database was created");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
