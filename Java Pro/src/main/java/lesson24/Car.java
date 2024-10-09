package lesson24;

public class Car {
    private String maker;
    private String model;
    private double price;

    public Car(String maker, String model, double price) {
        this.maker = maker;
        this.model = model;
        this.price = price;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "C{" +
                "k='" + maker + '\'' +
                ", m='" + model + '\'' +
                ", p=" + price +
                '}';
    }
}
