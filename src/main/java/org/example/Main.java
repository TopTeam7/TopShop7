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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Это информационное сообщение");
        log.warn("Это предупреждающее сообщение");
        log.error("Это сообщение об ошибке");

        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository("src/main/resources/products.txt", "src/main/resources/idProducts_id.txt");

        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository);
        ProductService productService = new ProductService(productRepository);

        CustomerController customerController = new CustomerController(customerService);
        OrderController orderController = new OrderController(orderService);
        ProductController productController = new ProductController(productService);

        MainController mainController = new MainController(customerController, orderController, productController);
        mainController.start();
    }
}