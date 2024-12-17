package org.example.Repository;

import org.example.Model.Order;
import org.example.OrderException.OrderNotFoundExcetion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderRepository {

    Scanner scanner = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    private int orderCountId;
    private final List<Order> orders = new ArrayList<>();

    public OrderRepository() {
        this.orderCountId = 0;
    }

    public Order saveOrder(Order order) {
        order.setOrderId((++orderCountId));
        orders.add(order);
        return order;
    }

    public List<Order> listOrder() {
        return orders;
    }

    public void changeStatusOrder() {
        try {
            System.out.println("Enter order Id");
            int orderId = scanner.nextInt();

            System.out.println("Enter new status of the order");
            String newStatus = sc.nextLine();

            orders.get(orderId - 1).setOrderStatus(newStatus);
            System.out.println("Статус заказа " + orders.get(orderId - 1) + "  изменён");

        } catch (IndexOutOfBoundsException e) {
            throw new OrderNotFoundExcetion("Заказа с таким номером не существует");
        }
    }

    public Order getOrderById(int id) {
        try {
            return orders.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new OrderNotFoundExcetion("Заказа с таким номером не существует");
        }
    }
}
