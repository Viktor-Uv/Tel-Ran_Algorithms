package lesson44;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H_AutoIncrement {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:shop.db";

        /*
            executed in console:
            create table users (id integer not null primary key autoincrement, first text, last text);
         */

        try (
                Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
        ) {
            int rowsAffected = stmt.executeUpdate(
//                    "insert into users (first, last) values ('Max', 'Kotkov'); "
                    "INSERT INTO users (first, last) VALUES ('Sveta', 'Petrova'); "
            );

            if (rowsAffected > 0) {
                try (
                        // https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html#getGeneratedKeys--
                        ResultSet rs = stmt.getGeneratedKeys();
                ) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("key is: " + id);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
