package org.example;

import org.example.Controller.CustomerController;
import org.example.Controller.MainController;
import org.example.Controller.OrderController;
import org.example.Controller.ProductController;

/**
 * Главный класс для запуска приложения.
 */
public class Main {
    public static void main(String[] args) {

        CustomerController customerController = new CustomerController();
        OrderController orderController = new OrderController();
        ProductController productController = new ProductController();

                MainController mainController = new MainController(customerController, orderController, productController);
        mainController.start();
    }
}