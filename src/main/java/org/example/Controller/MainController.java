package org.example.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Главный контроллер для управления приложением.
 */
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private boolean cycleProgram = true;
    private final CustomerController customerController;
    private final OrderController orderController;
    private final ProductController productController;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор для создания MainController.
     *
     * @param customerController контроллер для управления покупателями
     * @param orderController    контроллер для управления заказами
     * @param productController  контроллер для управления продуктами
     */
    public MainController(CustomerController customerController, OrderController orderController, ProductController productController) {
        this.customerController = customerController;
        this.orderController = orderController;
        this.productController = productController;
    }

    /**
     * Запускает главное меню программы.
     */
    public void start() {
        while (cycleProgram) {
            System.out.println("\nМеню:");
            System.out.println("1. Управление покупателями");
            System.out.println("2. Управление продуктами");
            System.out.println("3. Управление заказами");
            System.out.println("0. Выход из программы");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> startCustomer();
                    case 2 -> startProduct();
                    case 3 -> startOrder();
                    case 0 -> closeController();
                    default -> log.info("Неверный выбор. Попробуйте снова.");
                }
            } catch (RuntimeException e) {
                log.error("Ошибка: ", e);
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

    /**
     * Переходит в меню управления покупателями.
     */
    private void startCustomer() {
        log.info("Начало управления покупателями");
        customerController.startCustomer(cycleProgram);
    }

    /**
     * Переходит в меню управления продуктами.
     */
    public void startProduct() {
        log.info("Начало управления продуктом");
        productController.startProduct(cycleProgram);
    }
    /**
     * Переходит в меню управления заказами.
     */
    private void startOrder() {
        log.info("Начало управления заказами");
        orderController.startOrder(cycleProgram);
    }

    /**
     * Завершает работу программы.
     */
    private void closeController() {
        log.info("Завершение работы программы");
        cycleProgram = false;
    }
}