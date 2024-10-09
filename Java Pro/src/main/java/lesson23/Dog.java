package lesson23;

import java.io.Serializable;
// Serializable marks Object that it can be serialized in the IO-stream
public class Dog implements Serializable {
    private String name;
    private int age;
    private boolean isMean;

    public Dog(String name, int age, boolean isMean) {
        this.name = name;
        this.age = age;
        this.isMean = isMean;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMean() {
        return isMean;
    }

    @Override
    public String toString() {
        return "D{" +
                "n='" + name + '\'' +
                ", a=" + age +
                ", m=" + isMean +
                '}';
    }
}
