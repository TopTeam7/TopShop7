 package org.example;

import org.example.Controller.CustomerController;
import org.example.Controller.MainController;
import org.example.Controller.OrderController;
import org.example.Controller.ProductController;
import org.example.Repository.CustomerRepository;
import org.example.Repository.OrderRepository;
import org.example.Repository.ProductRepository;
import org.example.Service.CustomerService;
import org.example.Service.OrderService;
import org.example.Service.ProductService;

public class Main {
    public static void main(String[] args) {

        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();

        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository);
        ProductService productService = new ProductService(productRepository) ;

        CustomerController customerController = new CustomerController(customerService);
        OrderController orderController = new OrderController(orderService);
        ProductController productController = new ProductController(productService);

        MainController mainController = new MainController(customerController, orderController, productController);

        mainController.start();
    }
}