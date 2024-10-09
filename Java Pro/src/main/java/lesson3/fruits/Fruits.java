package lesson3.fruits;

public enum Fruits {
    APPLE(52), // 52
    ORANGE(47), // 47
    BANANA(89), // 89
    GRAPES(67), // 67
    KIWI(61); // 61

    private int calories;

    public int getCalories() {
        return calories;
    }

    Fruits(int calories) {
        this.calories = calories;
    }

    public static void main(String[] args) {
        Fruits a = APPLE;
        System.out.println(a + " calories: " + a.getCalories());
    }
}
