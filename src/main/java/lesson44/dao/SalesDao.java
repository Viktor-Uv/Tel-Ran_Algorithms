package lesson44.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO - Data Access Object

/* Reference:
    create table salespeople (
        snum int primary key not null,
        sname text not null,
        city text not null,
        comm integer not null
        );
 */

public class SalesDao {

    private static String url = "jdbc:sqlite:shop.db";

    public Sales getSalesById(int id) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT snum, sname, city, comm FROM salespeople WHERE snum=?"
                );
        ) {
            pstmt.setInt(1, id);
            try (
                    ResultSet rs = pstmt.executeQuery()
            ) {
                while (rs.next()) {
                    int snum = rs.getInt("snum");
                    String sname = rs.getString("sname");
                    String city = rs.getString("city");
                    int comm = rs.getInt("comm");
                    return new Sales(snum, sname, city, comm);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Sales> getAll() {
        List<Sales> salesmen = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(url);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM salespeople");
        ) {
            while (rs.next()) {
                int snum = rs.getInt("snum");
                String sname = rs.getString("sname");
                String city = rs.getString("city");
                int comm = rs.getInt("comm");
                salesmen.add(new Sales(snum, sname, city, comm));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return salesmen;
    }

    public int create(Sales sales) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO salespeople VALUES (?, ?, ?, ?);"
                );
        ) {
            pstmt.setInt(1, sales.getSnum());
            pstmt.setString(2, sales.getSname());
            pstmt.setString(3, sales.getCity());
            pstmt.setInt(4, sales.getComm());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    public int delete(Sales sales) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "DELETE FROM salespeople WHERE snum = ?; "
                );
        ) {
            pstmt.setInt(1, sales.getSnum());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    public int update(Sales sales) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "UPDATE salespeople SET sname = ?, city = ?, comm = ? WHERE snum = ?; "
                );
        ) {
            pstmt.setString(1, sales.getSname());
            pstmt.setString(2, sales.getCity());
            pstmt.setInt(3, sales.getComm());
            pstmt.setInt(4, sales.getSnum());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }
}

/* Injection example:
    last = "'Kotkov'; drop table users;"
    "insert into users (first, last) values ("+first+", "+last+");"
 */
