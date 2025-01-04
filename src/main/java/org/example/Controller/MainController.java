package org.example.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class); // Логгер для логирования событий

    private boolean cycleProgram = true; // Флаг для управления циклом программы
    private final OrderController orderController; // Контроллер для управления заказами
    private final ProductController productController; // Контроллер для управления продуктами

    Scanner scanner = new Scanner(System.in); // Сканер для ввода данных с консоли

    public MainController(CustomerController customerController, OrderController orderController, ProductController productController) {
        this.orderController = orderController;
        this.productController = productController;
    }

    /**
     * Метод не принимает параметры.
     * Метод запускает главное меню программы
     */
    public void start() {
        while (cycleProgram) { // Цикл выполняется, пока cycleProgram равен true
            System.out.println("================= Главное меню =================");
            System.out.println("Для управления покупателями нажмите цифру 1");
            System.out.println("Для управления продуктами нажмите цифру 2");
            System.out.println("Для управления заказами нажмите цифру 3");
            System.out.println("Для выхода из программы нажмите цифру 0");
            int choice = scanner.nextInt(); // Считывание выбора пользователя
            try {
                switch (choice) {
                    case 1 -> startCustomer(); // Переход в меню управления покупателями
                    case 2 -> startProduct(); // Переход в меню управления продуктами
                    case 3 -> startOrder(); // Переход в меню управления заказами
                    case 0 -> closeController(); // Завершение работы программы
                    default -> log.info("Неверный выбор"); // Логирование неверного выбора
                }
            } catch (RuntimeException e) {
                log.error("Ошибка: ", e); // Логирование ошибки
            }
        }
    }

    /**
     * Метод не принимает параметры.
     * Метод переходит в меню товары
     */
    public void startProduct() {
        log.info("Начало управления продуктом");
        productController.startProduct(cycleProgram); // Запуск управления продуктами
    }

    /**
     * Метод не принимает параметры.
     * Метод переходит в меню Заказ
     */
    public void startOrder() {
        log.info("Управление начальными заказами");
        orderController.startOrder(cycleProgram); // Запуск управления заказами
    }

    /**
     * Метод не принимает параметры.
     * Метод завершает работу программы
     */
    public void closeController() {
        log.info("Закрытие программы");
        cycleProgram = false; // Установка флага для завершения цикла
    }

    /**
     * Метод не принимает параметры.
     * Метод переходит в меню Покупатели
     */
    public void startCustomer() {
        log.info("Начало работы с клиентами");
        // Реализация для управления покупателями
    }
}
