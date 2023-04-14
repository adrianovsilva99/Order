package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter a client data:");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        LocalDate date = LocalDate.parse(sc.next(), fmt);

        Client client = new Client(name, email, date);

        System.out.println("--------------------------------");

        System.out.println("Enter order data:");
        System.out.print("Status: ");
        sc.nextLine();
        String orderStatus = sc.nextLine();
        Order order = new Order(LocalDateTime.now(), OrderStatus.valueOf(orderStatus), client);

        System.out.print("How many items to this order: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter #" + (i + 1) + " item data:");
            System.out.print("Product name: ");
            sc.nextLine();
            String productName = sc.nextLine();
            System.out.print("Product price: ");
            Double productPrice = sc.nextDouble();
            System.out.print("Quantity: ");
            int quantity = sc.nextInt();

            OrderItem orderItems = new OrderItem(quantity, productPrice,
                    new Product(productName, productPrice));

            order.addItem(orderItems);
        }

        System.out.println("--------------------------------");

        System.out.println("ORDER SUMARY:");
        System.out.println(order);


        sc.close();
    }
}