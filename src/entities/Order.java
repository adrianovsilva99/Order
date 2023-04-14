package entities;

import entities.enums.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    DateTimeFormatter fmtNow = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:MM:ss");
    private LocalDateTime moment;
    private OrderStatus status;

    private Client client;
    private List<OrderItem> items = new ArrayList<>();

    public Order(LocalDateTime moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public Double total() {
        double total = 0;
        for (OrderItem c : items) {
            total += c.subTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Order moment: ");
        sb.append(String.format(fmtNow.format(moment)) + "\n");
        sb.append("Order status: ");
        sb.append(status + "\n");
        sb.append("Client: ");
        sb.append(client.getName());
        sb.append(" (" + client.getBirthDate() + ") - ");
        sb.append(client.getEmail() + "\n");
        sb.append("Order items:\n");
        for (OrderItem c : items) {
            sb.append(c.getProduct());
            sb.append("Quantity: ");
            sb.append(c.getQuantity() + ", ");
            sb.append("Subtotal: $");
            sb.append(String.format("%.2f", c.subTotal()) + "\n");
        }
        sb.append("Total price: ");
        sb.append(total());

        return sb.toString();
    }
}