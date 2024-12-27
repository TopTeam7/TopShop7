package org.example.Controller;

import java.io.IOException;
import java.util.Scanner;

public class MainController {
    private final CustomerController customerController;
    private final OrderController orderController;
    private final ProductController productController;
    private final Scanner scanner;

    public MainController(CustomerController customerController, OrderController orderController, ProductController productController) {
        this.customerController = customerController;
        this.orderController = orderController;
        this.productController = productController;
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        while (true) {
            System.out.println("===== Главное меню =====");
            System.out.println("1. Управление покупателями");
            System.out.println("2. Управление заказами");
            System.out.println("3. Управление товарами");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> customerController.showMenu();
                case 2 -> orderController.showMenu();
                case 3 -> productController.showMenu();
                case 0 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}