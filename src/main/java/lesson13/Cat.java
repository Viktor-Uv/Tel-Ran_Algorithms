package lesson13;

import java.util.Objects;

public class Cat implements Comparable<Cat> {
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

//    @Override
//    public boolean equals(Object o)
//    {
//        if(this == o)
//            return true;
//        if(o == null)
//            return false;
//        if(!(o instanceof Cat))
//            return false;
//        Cat c = (Cat) o;
//        return this.getName().equals(c.getName()) && getAge() == c.getAge();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (age != cat.age) return false;
        return Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public int compareTo(Cat cat) {
        // this
        // cat
        // return getAge() - cat.getAge();
        return Integer.compare(getAge(), cat.getAge());
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}