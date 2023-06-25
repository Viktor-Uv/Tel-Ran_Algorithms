package lesson3.pizza;

public class Pizza {
    private int peperoni;
    private int mashroom;
    private int cheese;
    // Small, Medium, Large
    // private String size;
    private PizzaSize size;

    public Pizza(int peperoni, int mashroom, int cheese, PizzaSize size) {
        this.peperoni = peperoni;
        this.mashroom = mashroom;
        this.cheese = cheese;
        this.size = size;
    }

    // only 3 possible sizes, no any other
    // SMALL, MEDIUM, BIG
    // can't be int, can't be String

    public double price() {
        double price = 0;
//        if (size.equals("Small"))
//            price += 10;
//        else if (size.equals("Medium"))
//            price += 12;
//        else
//            price += 14;
        switch (size) {
            case SMALL -> price += 10;
            case MEDIUM -> price += 12;
            case BIG -> price += 14;
        }
        price += (peperoni + cheese + mashroom) * 2;
        return price;
    }

    public static void main(String[] args) {
        Pizza p1 = new Pizza(1, 2, 1, PizzaSize.SMALL);
    }
}
