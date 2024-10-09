package lesson44;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class F_Delete {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:shop.db";

        try (
                Connection conn = DriverManager.getConnection(url);
                // Request:
                Statement stmt = conn.createStatement();
        ) {
            // удалите заказы у которых дата '1990-10-03'
            // распечатайте количество удаленных строк
            int deletedRows = stmt.executeUpdate(
                    "DELETE FROM orders WHERE odate = '1990-10-03'; "
            );
            System.out.println("rows deleted: " + deletedRows);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
