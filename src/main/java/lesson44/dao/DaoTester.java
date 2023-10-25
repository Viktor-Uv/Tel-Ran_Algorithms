package lesson44.dao;

import java.util.List;

// DAO - Data Access Object

public class DaoTester {
    public static void main(String[] args) {
        SalesDao salesDao = new SalesDao();
        Sales s1001 = salesDao.getSalesById(1001);
        System.out.println(s1001);

        System.out.println("============");

        Sales max = new Sales(2002, "Max Kotkov", "Prague", 8);
        System.out.println("Rows inserted: " + salesDao.create(max));

        System.out.println("Rows deleted: " + salesDao.delete(max));

        System.out.println("============");

        Sales axel = salesDao.getSalesById(1003);
        axel.setComm(14);
        System.out.println("Rows updated: " + salesDao.update(axel));

        System.out.println("============");

        List<Sales> sales = salesDao.getAll();
        sales.forEach(
                System.out::println
        );
    }
}
