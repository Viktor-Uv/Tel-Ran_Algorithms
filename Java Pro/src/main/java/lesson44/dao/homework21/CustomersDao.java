package lesson44.dao.homework21;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao {
    private static String url = "jdbc:sqlite:shop.db";

    public Customer getCustomerById(int id) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT * FROM customers WHERE cnum = ?"
                );
        ) {
            pstmt.setInt(1, id);
            try (
                    ResultSet rs = pstmt.executeQuery()
            ) {
                while (rs.next()) {
                    int cnum = rs.getInt("cnum");
                    String cname = rs.getString("cname");
                    String city = rs.getString("city");
                    int rating = rs.getInt("rating");
                    int snum = rs.getInt("snum");
                    return new Customer(cnum, cname, city, rating, snum);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(url);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM customers");
        ) {
            while (rs.next()) {
                int cnum = rs.getInt("cnum");
                String cname = rs.getString("cname");
                String city = rs.getString("city");
                int rating = rs.getInt("rating");
                int snum = rs.getInt("snum");
                customers.add(new Customer(cnum, cname, city, rating, snum));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return customers;
    }

    public int create(Customer customer) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO customers VALUES (?, ?, ?, ?, ?);"
                );
        ) {
            pstmt.setInt(1, customer.getCnum());
            pstmt.setString(2, customer.getCname());
            pstmt.setString(3, customer.getCity());
            pstmt.setInt(4, customer.getRating());
            pstmt.setInt(5, customer.getSnum());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    public int delete(Customer customer) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "DELETE FROM customers WHERE cnum = ?; "
                );
        ) {
            pstmt.setInt(1, customer.getCnum());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    public int update(Customer customer) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(
                        "UPDATE customers " +
                                " SET cname = ?, city = ?, rating = ?, snum = ? " +
                                " WHERE cnum = ?; "
                );
        ) {
            pstmt.setString(1, customer.getCname());
            pstmt.setString(2, customer.getCity());
            pstmt.setInt(3, customer.getRating());
            pstmt.setInt(4, customer.getSnum());
            pstmt.setInt(5, customer.getCnum());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }
}
