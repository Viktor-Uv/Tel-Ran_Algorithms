package lesson20;

public class OrderItem {
    private String title;
    private int quantity;
    private double unitPrice;

    public OrderItem(String title, int quantity, double unitPrice) {
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
