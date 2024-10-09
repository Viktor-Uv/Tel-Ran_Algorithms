package lesson39.reflection;

import lesson39.annotation.Call;

import java.lang.reflect.Method;

public class Sample {
    private String name;
    private int age = 43;

    public Sample() {
        name = "Java reflection API";
    }

    public Sample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greetings() {
        System.out.println("Name is: " + name);
    }

    public void jump(int height) {
        System.out.println("Jumping to the height of " + height);
    }

    private void secret() {
        System.out.println("It's a secret!");
    }

    // добавьте в Sample метод
    // public void callServer()
    // добавьте этому методу аннотацию Call (url="https://www.google.com", method="POST")
    @Call(url="https://www.google.com", method="POST")
    public void callServer() {
        // получите и распечатайте параметры из аннотации через запятую
        Class<?> classs = this.getClass();
        Method callServerMethod = null;
        try {
            callServerMethod = classs.getDeclaredMethod("callServer");
            Call call = callServerMethod.getAnnotation(Call.class);
            System.out.println(
                    "url: " + call.url() + ", method: " + call.method() +
                            ", login: " + call.login() + ", password: " + call.password()
            );
            // вызовите этот метод у любого экземпляра
        } catch (NoSuchMethodException e) {
            System.err.println(e.getMessage());
        }
    }
}
