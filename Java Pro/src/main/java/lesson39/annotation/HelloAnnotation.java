package lesson39.annotation;

// annotations can be only declared as @interface

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // RUNTIME - means will apply at program run
public @interface HelloAnnotation {
    String name();
    String value();
    int age() default 25; // теперь этот параметр можно не указывать
}
