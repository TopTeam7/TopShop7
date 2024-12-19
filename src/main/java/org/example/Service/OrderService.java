package org.example.Service;

import org.example.Model.Order;
import org.example.Repository.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order addOrder(int buyerId, int productId, String orderStatus) {
        Order newOrder = new Order(null, buyerId, orderStatus, productId);
        Order savedOrder = orderRepository.saveOrder(newOrder);
        return savedOrder;
    }

    public List<Order> listToView() {
        return orderRepository.listOrder();
    }

    public void changeStatusOrder() {
       orderRepository.changeStatusOrder();
    }
    public Order getOrderById(int id){
       return orderRepository.getOrderById(id);
    }
}
