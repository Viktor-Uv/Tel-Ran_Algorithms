package lesson15.hashmap;

public interface MyMap {
    int size(); // количество пар
    boolean contains(String key); // содержится ли пара с таким ключом
    void put(String key, String value); // сохранение пары
    String get(String key); // возвращение значения по ключу
    String remove(String key); // удаление пары по ключу
}
