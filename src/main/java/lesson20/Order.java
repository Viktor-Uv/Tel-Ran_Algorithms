package lesson20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// заказ/чек
public class Order {
    private List<OrderItem> items = new ArrayList<>();
    public Order(OrderItem ... items) { // varargs - this means it can take any number of items
        this.items.addAll(Arrays.asList(items));
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
