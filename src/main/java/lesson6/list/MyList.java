package lesson6.list;

// интерфейс это шаблон - набор методов, которые должны
// реализовываться его потомками
public interface MyList {
    int size(); // размер
    boolean contains(int value); // содержится ли в листе такое значение
    void set(int index, int value); // изменение значения элемента по индексу
    void add(int value); // добавление элемента в конец
    void add(int index, int value); // вставка элемента
    void remove(int index); // удаление элемента по индексу
    int get(int index); // получение значения по индексу
}
