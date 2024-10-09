package lesson14;

public class Fruit {
    private String name;
    private int calories;

    public Fruit(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "F{" +
                "n='" + name + '\'' +
                ", c=" + calories +
                '}';
    }
}
