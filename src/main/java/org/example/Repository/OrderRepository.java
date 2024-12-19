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

    /** Метод принимает параметр
      * @param order
     * Метод добавляет новы заказ в лист заказов и присвавает Id номер
     * @return Order
     */
    public Order saveOrder(Order order) {
        order.setOrderId((++orderCountId));
        orders.add(order);
        return order;
    }

    /**Метод не принимает параметров
     * Метод возврфщает лист заказов
     * @return List<Order>
     */
    public List<Order> listOrder() {
        return orders;
    }

    /**Метод принимает параметры
      * @param orderId типа int
     * @param newStatus типа String
     * Метод достает заказ из листа по Id номеру меняет его статус
     */
    public void changeStatusOrder(int orderId, String newStatus) {
        try {
            orders.get(orderId - 1).setOrderStatus(newStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new OrderNotFoundExcetion("Заказа с таким номером не существует");
        }
    }

    /**
     * Метод принимает параметр
      * @param id типа int
     *  Метод возвращает заказ по номеру Id
     * @return Order
     */
    public Order getOrderById(int id) {
        try {
            return orders.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new OrderNotFoundExcetion("Заказа с таким номером не существует");
        }
    }
}
