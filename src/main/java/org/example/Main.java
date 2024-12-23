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

        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();


        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository);


        CustomerController customerController = new CustomerController(customerService);
        OrderController orderController = new OrderController(orderService);


        MainController mainController = new MainController(customerController, orderController);


        mainController.start();
    }
}