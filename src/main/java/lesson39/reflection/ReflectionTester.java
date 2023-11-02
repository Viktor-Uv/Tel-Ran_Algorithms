package lesson39.reflection;

/*
    Java Reflection — это особенный функционал, который позволяет программе
        получить доступ к приватным частям объектов или поменять поведение некоторых
        методов классов. Созданный таким образом код будет адаптироваться к входным
        данным и, например, не будет зависеть от типов, с которыми работает

    Methods in java.lang.reflect
        • getName() — возвращает имя и пакет класса;
        • getSimpleName() — возвращает имя класса без пакета;
        • getModifiers() — возвращает модификаторы класса;
        • getSuperclass() — возвращает родительский класс;
        • getInterfaces() — возвращает список интерфейсов, которые наследует класс;
        • getConstructors() — возвращает список конструкторов класса;
        • getFields() — возвращает список публичных полей класса;
        • getDeclaredFields() — возвращает список всех полей класса, в том числе приватных;
        • getMethods() — возвращает массив публичных методов класса;
        • getDeclaredMethods() — возвращает массив всех методов класса, в том числе приватных;
        • getPackage() — возвращает имя пакета класса.

     С помощью интерфейса Java Reflection API можно делать следующее:
        1. Определить класс объекта.
        2. Получить информацию о модификаторах класса, полях, методах, конструкторах и суперклассах.
        3. Выяснить, какие константы и методы принадлежат интерфейсу.
        4. Создать экземпляр класса, имя которого неизвестно до момента выполнения программы.
        5. Получить и установить значение свойства объекта.
        6. Вызвать метод объекта.
        7. Создать новый массив, размер и тип компонентов которого неизвестны до момента выполнения программ

    Reflection позволяет обратиться к каждому классу, как некоторой базе данных,
        чтобы узнать, из чего этот класс состоит (поля, методы, аннотации)
        Применяется при работе с библиотеками (просмотр, изменение), изменения видимости
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTester {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // получим класс Sample в виде объекта
        // Class<?> classs = Class.forName("lesson39.reflection.Sample");
        Class<?> classs = Class.forName(Sample.class.getName()); // same thing as above

        // ссылка на безаргументный конструктор класса
        Constructor<?> noArgsConstructor = classs.getConstructor();

        // создание объекта используя ссылку на его конструктор
        Sample sampleNoArgs = (Sample) noArgsConstructor.newInstance();
        // вызов метода созданного экземпляра
        sampleNoArgs.greetings();

        // String name, int age
        // получение ссылки на конструктор с параметрами
        Constructor<?> allArgsConstructor = classs.getConstructor(String.class, int.class);
        // вызовите конструктор с параметрами "Hello", 32
        Sample sample = (Sample) allArgsConstructor.newInstance("Hello", 32);
        sample.greetings();

        // Print all methods on the class
        System.out.println("\n-public methods");
        Method[] methods = classs.getMethods();
        for (Method m: methods) {
            System.out.println("Method name: " + m.getName());
        }

        System.out.println(); // new line

        // ссылка на метод с определенным типом аргумента
        Method jumpMethod = classs.getDeclaredMethod("jump", int.class);
        // вызов метода для экземпляра класса с передачей параметра
        jumpMethod.invoke(sample, 17);

        System.out.println(); // new line

        // вызовите через reflection метод greetings для sampleNoArgs
        Method greetingMethod = classs.getDeclaredMethod("greetings");
        greetingMethod.invoke(sampleNoArgs);

        System.out.println(); // new line

        // получаем поле по имени
        Field nameField = classs.getDeclaredField("name");
        // меняем права доступа к полю
        nameField.setAccessible(true); // private -> public
        nameField.set(sampleNoArgs, "hello 123");
        greetingMethod.invoke(sampleNoArgs);

        // создайте ссылку на метод secret
        Method secretMethod = classs.getDeclaredMethod("secret");
        // сделайте метод secret публичным
        secretMethod.setAccessible(true);
        // вызовите его для sample
        secretMethod.invoke(sample);

        System.out.println(); // new line

        sample.callServer();

    }
}
