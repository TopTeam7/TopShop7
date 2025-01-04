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
    private static final Logger log = LoggerFactory.getLogger(Main.class); // Логгер для логирования событий

    public static void main(String[] args) {
        log.info("Это информационное сообщение"); // Логирование информационного сообщения
        log.warn("Это предупреждающее сообщение"); // Логирование предупреждающего сообщения
        log.error("Это сообщение об ошибке"); // Логирование сообщения об ошибке

        CustomerRepository customerRepository = new CustomerRepository(); // Создание экземпляра репозитория для управления покупателями
        OrderRepository orderRepository = new OrderRepository(); // Создание экземпляра репозитория для управления заказами
        ProductRepository productRepository = new ProductRepository("src/main/resources/products.txt", "src/main/resources/idProducts_id.txt"); // Создание экземпляра репозитория для управления продуктами

        CustomerService customerService = new CustomerService(customerRepository); // Создание сервиса для управления покупателями
        OrderService orderService = new OrderService(orderRepository); // Создание сервиса для управления заказами
        ProductService productService = new ProductService(productRepository); // Создание сервиса для управления продуктами

        CustomerController customerController = new CustomerController(customerService); // Создание контроллера для управления покупателями
        OrderController orderController = new OrderController(orderService); // Создание контроллера для управления заказами
        ProductController productController = new ProductController(productService); // Создание контроллера для управления продуктами

        MainController mainController = new MainController(customerController, orderController, productController); // Создание главного контроллера
        mainController.start(); // Запуск главного контроллера
    }
}
