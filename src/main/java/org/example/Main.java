package org.example;

import org.example.Controller.CustomerController;
import org.example.Controller.MainController;
import org.example.Controller.OrderController;
import org.example.Repository.CustomerRepository;
import org.example.Repository.OrderRepository;
import org.example.Service.CustomerService;
import org.example.Service.OrderService;

public class Main {
    public static void main(String[] args) {
        // Инициализация репозиториев
        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();

        // Инициализация сервисов
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository);

        // Инициализация контроллеров
        CustomerController customerController = new CustomerController(customerService);
        OrderController orderController = new OrderController(orderService);

        // Главный контроллер, который управляет всеми контроллерами
        MainController mainController = new MainController(customerController, orderController);

        // Запуск приложения
        mainController.start();
    }
}