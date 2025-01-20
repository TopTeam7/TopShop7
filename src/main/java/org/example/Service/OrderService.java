
package org.example.Service;

import org.example.Model.Order;
import org.example.Repository.OrderRepository;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Метод примает параметры
     *
     * @param customerId  типа int
     * @param productId   типа int
     * @param orderStatus типа String
     *                    Метод создает новый заказ
     * @return объект типа Order
     */
    public Order addOrder(int customerId, String orderStatus, String productId) {
        Order newOrder = new Order(null, customerId, orderStatus, productId);
        return orderRepository.saveOrder(newOrder);
    }

    /**
     * Метод не принимает параметров
     * Метод возвращает список заказов
     *
     * @return List<String>
     */
    public List<String> listToView() {
        return orderRepository.listOrder();
    }

    /**
     * Метод примает параметры
     *
     * @param orderId   типа int
     * @param newStatus типа String
     *                  Метод позваляет изменить статус заказа
     */
    public void changeStatusOrder(int orderId, String newStatus) {
        orderRepository.changeStatusOrder(orderId, newStatus);
    }

    /**
     * Метод примает параметр
     *
     * @param id типа int
     *           Метод возвращает заказ по номеру Id
     * @return Order
     */
    public Order findOrderById(int id) {
        return orderRepository.findOrderById(id);
    }

}
