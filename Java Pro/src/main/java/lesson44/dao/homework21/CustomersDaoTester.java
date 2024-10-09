package lesson44.dao.homework21;

public class CustomersDaoTester {
    public static void main(String[] args) {
        CustomersDao customersDao = new CustomersDao();

        System.out.println("=== Get by id ===");
        System.out.println(customersDao.getCustomerById(2001));

        System.out.println("\n=== Get all ===");
        customersDao.getAll().forEach(
                System.out::println
        );

        System.out.println("\n=== Create ===");
        Customer jack = new Customer(4444, "Jack", "Oakland", 111, 1002);
        System.out.println("Rows inserted: " + customersDao.create(jack));
        System.out.println("Get Jack: " + customersDao.getCustomerById(jack.getCnum()));

        System.out.println("\n=== Update ===");
        jack.setRating(123);
        jack.setSnum(1007);
        System.out.println("Rows updated: " + customersDao.update(jack));
        System.out.println("Get Jack: " + customersDao.getCustomerById(jack.getCnum()));

        System.out.println("\n=== Delete ===");
        System.out.println("Rows deleted: " + customersDao.delete(jack));
        System.out.println("Get Jack: " + customersDao.getCustomerById(jack.getCnum()));
    }
}
