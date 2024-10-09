package lesson39.annotation;

/*
    Аннотации - это форма метаданных. Они предоставляют информацию о программе,
        при том сами частью программы не являются.

    Метаданные — это способ добавить дополнительную информацию к исходному коду

    Применение
        * Информация для компилятора. Могут использоваться компилятором для
            обнаружения ошибок и подавления предупреждений.

        * Обработка во время компиляции и развертывания. Программа может
            создавать код, XML-файлы и т.п. на основе аннотаций.

        * Обработка во время выполнения. Некоторые аннотации могут использоваться
            во время выполнения программы

    Классификация аннотаций
        • аннотации для аннотаций, еще называют мета-аннотациями:
            • @Target: указывает контекст, для которого применима аннотация

        • аннотации типов:
            • @Retention: указывает, до какого шага во время компиляции аннотация будет доступна
            • @Documented: указывает, что аннотация должна быть задокументирована с помощью javadoc
            • @Inherited: позволяет реализовать наследование аннотаций родительского
                класса классом-наследником
            • @Repeatable: указывает, что аннотация может быть использована повторно в том же месте

        • аннотации для кода:
            • @Override: указывает, что метод переопределяет, объявленный в суперклассе
                или интерфейсе метод
            • @Deprecated: помечает код, как устаревший
            • @SuppressWarnings: отключает для аннотированного элемента
                предупреждения компилятора. Обратите внимание, что если необходимо
                отключить несколько категорий предупреждений, их следует добавить в
                фигурные скобки, например @SuppressWarnings ({"unchecked", "cast"}).
            • @SafeVarargs: отключает предупреждения для всех методов или
                конструкторов, принимающих в качестве параметра varargs
            • @FunctionalInterface: помечает интерфейсы, имеющие только один
                абстрактный метод (при этом они могут содержать любое количество методов по
                умолчанию или статических)

        • нативные аннотации:
            @Native, применимая только к полям. Она указывает, что
                аннотированное поле является константой, на которую можно ссылаться с
                нативного кода

        • аннотации, написанные программистом:
            Чтобы создать аннотацию, мы используем ключевое слово interface и добавляем
                перед ним символ @. Символ @ сообщит компилятору, что это аннотация.
                Все аннотации расширяют java.lang.annotation.Annotation интерфейс, что означает,
                что java.lang.annotation.Annotation - это суперинтерфейс всех аннотаций.
                Аннотация должна иметь RetentionPolicy область действия аннотации, в которой в
                этот момент аннотация будет игнорироваться или отбрасываться. Если политика
                хранения не определена, будет использоваться политика хранения по
                умолчанию, которая представляет собой файл RetentionPolicy.CLASS.
 */

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@HelloAnnotation(name = "Max", value = "Hello")
public class AnnotationTester {
    // Use reflection to work with annotations in Java
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // Get the class object for AnnotationTester using its fully-qualified name
        Class<?> classs = Class.forName("lesson39.annotation.AnnotationTester");
        Annotation[] annotations = classs.getAnnotations();

        // Print types of all annotations on the class
        System.out.println("\n-Annotation Types:");
        for (Annotation a : annotations) {
            System.out.println("type: " + a.annotationType());
        } // Result -> type: interface lesson39.annotation.HelloAnnotation

        // // Get the class-level annotation (HelloAnnotation) on AnnotationTester and print its values
        HelloAnnotation helloAnnotation = classs.getAnnotation(HelloAnnotation.class);
        System.out.println("name: " + helloAnnotation.name() + ", value " + helloAnnotation.value());

        // Use reflection to get a reference to the "doSomething" method
        Method doSomething = classs.getMethod("doSomething");


        // Get the method-level annotation (HelloAnnotation) on the "doSomething" method
        helloAnnotation = doSomething.getAnnotation(HelloAnnotation.class);
        // Print the values of its parameters
        System.out.println("name: " + helloAnnotation.name() + ", value " + helloAnnotation.value());

    } // Main

    // Define a method with a method-level HelloAnnotation
    @HelloAnnotation(name = "do", value = "something", age = 36)
    public void doSomething() {
        // This method doesn't do anything but is annotated with HelloAnnotation
    }
}
