package org.example.Controller;

import org.example.OrderException.OrderNotFoundExcetion;

import java.util.Scanner;

public class MainController {
    private boolean cycleProgram = true;
    private final OrderController orderController;
    Scanner scanner = new Scanner(System.in);

    public MainController(CustomerController customerController, OrderController orderController) {
        this.orderController = orderController;
    }

    /**Метод нe принимает параметры
     * Метод запускает главное меню программы
     *
      */
    public void start() {
        while (cycleProgram) {
            System.out.println("Для управления покупателями нажмите цифру 1");
            System.out.println("Для управления продуктами нажмите цифру 2");
            System.out.println("Для управления заказами нажмите цифру 3");
            System.out.println("Для выхода их программы нажмите цифру 0");
            int choice = scanner.nextInt();
            try {
                switch (choice) {

                    // здесь добавить вызов методов для покупателя и продукта

                    case 3 -> startOrder();
                    default -> closeController();
                }
            } catch (OrderNotFoundExcetion e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Метод не принимает параметры
     * Метод переходит в меню Заказ
     */
    public void startOrder() {

        orderController.startOrder(cycleProgram);
    }

    /**
     * Метод не принимает
     * Метод зпрекращает работу программы
     */
    public void closeController() {
        cycleProgram = false;
    }


}