
package org.example.Controller;



import java.util.Scanner;

public class MainController {
    private boolean cycleProgram = true;
    private final OrderController orderController;
    private final ProductController productController;

    Scanner scanner = new Scanner(System.in);

    public MainController(CustomerController customerController, OrderController orderController, ProductController productController) {
        this.orderController = orderController;
        this.productController = productController;
    }

    /**
     * Метод не принимает параметры
     * Метод запускает главное меню программы
     */
    public void start() {
        while (cycleProgram) {
            System.out.println("Для управления покупателями нажмите цифру 1");
            System.out.println("Для управления продуктами нажмите цифру 2");
            System.out.println("Для управления заказами нажмите цифру 3");
            System.out.println("Для выхода из программы нажмите цифру 0");
            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1 -> startCustomer();
                    case 2 -> startProduct();
                    case 3 -> startOrder();
                    case 0 -> closeController();
                    default -> System.out.println("Неверный выбор");
                }
            } catch (RuntimeException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Метод не принимает параметры
     * Метод переходит в меню товары
     */
    public void startProduct() {
        productController.startProduct(cycleProgram);
    }

    /**
     * Метод не принимает параметры
     * Метод переходит в меню Заказ
     */
    public void startOrder() {
        orderController.startOrder(cycleProgram);
    }

    /**
     * Метод не принимает параметры
     * Метод завершает работу программы
     */
    public void closeController() {
        cycleProgram = false;
    }

    /**
     * Метод не принимает параметры
     * Метод переходит в меню Покупатели
     */
    public void startCustomer() {
        // Реализация для управления покупателями
    }
}
