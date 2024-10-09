package lesson39.annotation;

/*
    Создайте аннотацию Call
    со строковыми параметрами
    url
    method
    login с дефолтным значением user
    password c дефолтным значением secret
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Call {
    String url();
    String method();
    String login() default "user";
    String password() default "secret";
}
