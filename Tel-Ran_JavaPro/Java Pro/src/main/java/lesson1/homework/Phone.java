package lesson1.homework;

public class Phone {
    private final String number;
    private final String model;
    private final int weight;

    // Constructor
    public Phone(String number, String model, int weight) {
        this.number = number;
        this.model = model;
        this.weight = weight;
    }

    // Methods
    public void printValues() {
        System.out.println(
                "Number: " + this.number +
                ", Model: " + this.model +
                ", Weight: " + this.weight
        );
    }
    public void receiveCall(String callerName) {
        System.out.println("Receiving call from " + callerName);
    }
    public String getNumber() {
        return number;
    }
}
