package org.example.Service;

import org.example.Model.Order;
import org.example.Repository.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    /**Метод примает параметры
     *
     * @param buyerId типа int
     * @param productId типа int
     * @param orderStatus типа String
     *  Метод создает новый заказ
     * @return объект типа Order
     */
    public Order addOrder(int buyerId, int productId, String orderStatus) {
        Order newOrder = new Order(null, buyerId, orderStatus, productId);
        Order savedOrder = orderRepository.saveOrder(newOrder);
        return savedOrder;
    }

    /**Метод не принимает параметров
     * Метод возвращает список заказов
     * @return List<Order>
     */
    public List<Order> listToView() {
        return orderRepository.listOrder();
    }

    /** Метод примает параметры
      * @param orderId типа int
     * @param newStatus типа String
     * Метод позваляет изменить статус заказа
     */
    public void changeStatusOrder(int orderId,String newStatus) {
       orderRepository.changeStatusOrder(orderId,newStatus);
    }

    /** Метод примает параметр
      * @param id типа int
     *  Метод возвращает заказ по номеру Id
     * @return Order
     */
    public Order getOrderById(int id){
       return orderRepository.getOrderById(id);
    }
}
