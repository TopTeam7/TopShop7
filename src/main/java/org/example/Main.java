package org.example;

import org.example.Controller.MainController;
import org.example.Controller.OrderController;
import org.example.Repository.OrderRepository;
import org.example.Service.OrderService;

public class Main {
    public static void main(String[] args) {

        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);
        OrderController orderController = new OrderController(orderService);
        MainController mainController = new MainController(orderController);

        // здесь добавить свои котроллеры, репозитории и сервисы

        mainController.start();


    }
}