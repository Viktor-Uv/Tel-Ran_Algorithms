package lesson14;

import java.util.*;

/**
 * JavaPro. Homework #6
 *
 * @author Viktor Uvarchev
 * @version 21 Aug 2023
 */

/*
 * Task 1. Создайте класс Employee с полями int id, String name, int age, double salary
 *         Реализуйте в этом классе Comparable<Employee> по полю id
 *         В виде статических полей добавьте компараторы для сортировки по возрасту,
 *         имени и по зарплате
 *
 * Task 2. Создайте коллекцию с сотрудниками и отсортируйте ее вначале по возрасту и
 *         потом по имени по убыванию
 *
 * Task 3**. Напишите статическую функцию, которая принимает на вход:
 *              коллекцию для сортировки
 *              список компараторов
 *         и сортирует переданную коллекцию составным компаратором из переданного списка
 *         public static void sortEmployees(List<Employee> emps, Collection<Comparator<Employee>> comps)
 */

public class Homework6 {
    public static void main(String[] args) {
        // Task 1 test:
        List<Employee> employees = new ArrayList<>(
                Arrays.asList(
                        new Employee(42, "Carl", 23, 5453),
                        new Employee(28, "Rebecca", 26, 4050),
                        new Employee(91, "Kate", 26, 3323),
                        new Employee(35, "Paul", 28, 3323),
                        new Employee(69, "Alissa", 23, 5788)
                )
        );
        System.out.println(employees.get(0).compareTo(employees.get(1)));
        employees.sort(Employee.salaryComparator);
        System.out.println(employees);

        // Task 2 test:
        Collections.sort(employees,
                Employee.ageComparator.thenComparing(
                        Employee.nameComparator.reversed()
                )
        );
        System.out.println(employees);

        // Task 3 test:
        sortEmployees(employees,
                new ArrayList<Comparator<Employee>>(
                        Arrays.asList(
                                Employee.nameComparator,
                                Employee.ageComparator.reversed(),
                                Employee.salaryComparator.reversed()
                        )
                )
        );
        System.out.println(employees);

    } // Main

    // Task 1 & 2 implementation:
    public static class Employee implements Comparable<Employee> {
        private int id;
        private String name;
        private int age;
        private double salary;

        public Employee(int id, String name, int age, double salary) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public int compareTo(Employee e) {
            return Integer.compare(getId(), e.getId());
        }

        public static Comparator<Employee> ageComparator =
                (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge());

        public static Comparator<Employee> nameComparator =
                (e1, e2) -> e1.getName().compareTo(e2.getName());

        public static Comparator<Employee> salaryComparator =
                (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary());

        @Override
        public String toString() {
            return "E{" +
                    "id=" + id +
                    ", n='" + name + '\'' +
                    ", a=" + age +
                    ", s=" + salary +
                    '}';
        }
    } // Employee class

    // Task 3 implementation:
    public static void sortEmployees(List<Employee> emps, Collection<Comparator<Employee>> comps) {
        if (comps == null) {
            return;
        }

        // Create new combined comparator
        Comparator<Employee> combinedComp = null;

        // Extend combined comparator by adding additional comparators
        for (Comparator<Employee> c : comps) {
            if (combinedComp == null) {
                combinedComp = c;
            } else {
                combinedComp = combinedComp.thenComparing(c);
            }
        }

        // Sort given list
        Collections.sort(emps, combinedComp);
    }
}
