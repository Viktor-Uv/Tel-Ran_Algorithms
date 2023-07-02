package lesson5.homework;

import java.util.Arrays;

/**
 * JavaPro. Homework #3
 *
 * @author Viktor Uvarchev
 * @version 02 Jul 2023
 */

/*
 * Task 1. Напишите функцию, принимающую на вход два
 * массива целых и возвращающую один массив, содержащий все их элементы
 * public static int [] concat(int [] a, int [] b)
 * Пример: {1,2,3}, {4,5} -> {1,2,3,4,5}
 *
 *
 * Task 2. Напишите функцию для "уплощения" передаваемого в нее двухмерного массива.
 * public static int [] flatten(int [] [] a)
 * То есть, если, например, в функцию передается массив {{1,2,3}, {4}}
 * то нужно выдавать массив {1,2,3,4}
 *
 *
 * Task *3.  Напишите функцию, которая найдет в двух массивах одинаковые элементы
 * и вернет их в виде массива
 * (порядок не важен)
 * public static int [] findDuplicates(int [] a, int [] b)
 * Пример: {2,3,1}, {4,5,3,2} -> {2,3}
 * Подумайте над оптимизацией - возможно, предварительная сортировка сможет как-то помочь?
 *
 *
 * Task *4.  Напишите функцию, которая найдет в двух массивах уникальные элементы
 * и вернет их в виде массива
 * (порядок не важен)
 * public static int [] findUnique(int [] a, int [] b)
 * Пример: {2,3,1}, {4,5,3,2} -> {1,4,5}
 * Подумайте над оптимизацией - возможно, предварительная сортировка сможет как-то помочь?
 */

public class Homework {
    public static void main(String[] args) {
        // Task 1
        int [] array1 = new int[] {1, 2, 3, 4, 5};
        int [] array2 = new int[] {6, 7, 8, 9};
        int [] array3 = concat(array1, array2);
        System.out.println("Task 1: " + Arrays.toString(array3));

        // Task 2
        int [][] array4 = new int[][] {
                {1, 2, 3, 4},
                {5, 6},
                {7, 8, 9}
        };
        int [] array5 = flatten(array4);
        System.out.println("Task 2: " + Arrays.toString(array5));

        // Task 3
        int [] array6 = new int[] {7, 5, 3, 4, 2};
        int [] array7 = new int[] {9, 7, 8, 2, 4, 1};
        int [] array8 = findDuplicates(array6, array7);
        System.out.println("Task 3: " + Arrays.toString(array8));

        // Task 4
        int [] array9  = new int[] {7, 5, 3, 4, 2};
        int [] array10 = new int[] {9, 7, 8, 2, 4, 1};
        int [] array11 = findUnique(array9, array10);
        System.out.println("Task 4: " + Arrays.toString(array11));

    }
    // Method for Task 1
    public static int [] concat(int [] a, int [] b) {
        int [] ab = new int[a.length + b.length];

        int index = 0;
        for (int i = 0; i < a.length; i++) {
            ab[index++] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            ab[index++] = b[i];
        }

        return ab;
    }

    // Method for Task 2
    public static int [] flatten(int [][] a) {
        int noOfElements = 0;
        for (int [] element : a) {
            noOfElements += element.length;
        }
        int [] b = new int[noOfElements];

        int index = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                b[index++] = a[i][j];
            }
        }

        return b;
    }

    // Method for Task 3
    public static int [] findDuplicates(int [] a, int [] b) {
        int [] common = new int[0];
        Arrays.sort(a);
        Arrays.sort(b);

        for (int element : a) {
            if (Arrays.binarySearch(b, element) >= 0) {
                common = addElementToArray(common, element);
            }
        }

        return common;
    }

    // Method for Task 4
    public static int [] findUnique(int [] a, int [] b) {
        int [] unique = new int[0];
        Arrays.sort(a);
        Arrays.sort(b);

        for (int element : a) {
            if (Arrays.binarySearch(b, element) < 0) {
                unique = addElementToArray(unique, element);
            }
        }

        return unique;
    }

    // Extends an array by adding one element to the end
    public static int [] addElementToArray(int [] a, int element) {
        int [] b = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        b[a.length] = element;
        return b;
    }
}
