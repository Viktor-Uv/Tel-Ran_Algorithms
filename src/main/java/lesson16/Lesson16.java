package lesson16; // Date 16.08.2023, 21.08.2023

import lesson16.MyBinaryTree.Vortex;

public class Lesson16 {
    // граф это совокупность ребер и вершин
    // вершина - Vortex
    // ребро - Edge - соединение между 2 вершинами
    // граф - полносвязный - можно по ребрам добраться из любой вершины в любую другую
    //        не полносвязный
    //        циклический - когда из одной вершины можно добраться по ребрам в нее же
    //        ациклический - никакие вершины не зациклены
    // дерево - ациклический и полносвязный граф
    // лемма - в дереве количество ребер равно (количеству вершин)-1

    // Бинарное дерево - дерево у каждой из вершин которого может быть максимум
    //                   две "дочерние" вершины
    public static void main(String[] args) {
        Vortex v5 = new Vortex(5);
        Vortex v7 = new Vortex(7);
        Vortex v6 = new Vortex(6, v5, v7);
        Vortex v10 = new Vortex(10);
        Vortex v8 = new Vortex(8, v6, v10);

        MyBinaryTree tree = new MyBinaryTree(v8);
        System.out.println(tree);

        System.out.println(tree.countVertices());
        System.out.println(tree.depth());

        tree.add(12);
        tree.add(14);
        System.out.println(tree);

    }
}
