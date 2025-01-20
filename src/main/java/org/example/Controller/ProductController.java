package org.example.Controller;

import org.example.Model.Product;
import org.example.Model.ProductCategory;
import org.example.OrderException.ProductNotFoundException;
import org.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Контроллер для управления товарами.
 */
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private boolean cycleProductProgram = true;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор для создания ProductController.
     *
     * @param productService сервис для работы с продуктами
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Запускает программу управления товарами.
     *
     * @param isCycleProgram флаг для управления циклом программы
     */
    public void startProduct(boolean isCycleProgram) {
        cycleProductProgram = isCycleProgram;
        while (cycleProductProgram) {
            System.out.println("===== Управление товарами =====");
            System.out.println("1. Добавить товар");
            System.out.println("2. Посмотреть все доступные товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера сканера

            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> getAllProducts();
                    case 3 -> findProductById();
                    case 0 -> back();
                    default -> {
                        log.error("Неверный выбор");
                        System.out.println("Неверный выбор. Попробуйте снова.");
                    }
                }
            } catch (ProductNotFoundException e) {
                log.error("Ошибка: ", e);
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                log.error("Непредвиденная ошибка: ", e);
                System.out.println("Непредвиденная ошибка: " + e.getMessage());
            }
        }
    }

    /**
     * Добавляет новый товар.
     */
    private void addProduct() {
        try {
            System.out.print("Введите название товара: ");
            String title = scanner.nextLine();

            System.out.print("Введите цену товара: ");
            int price = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера сканера

            System.out.println("Выберите категорию товара:");
            System.out.println("1. " + ProductCategory.FOOD.getProductCategory());
            System.out.println("2. " + ProductCategory.ELECTRONICS.getProductCategory());
            System.out.println("3. " + ProductCategory.CLOTHING.getProductCategory());
            int categoryNum = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера сканера

            String category;
            switch (categoryNum) {
                case 1 -> category = ProductCategory.FOOD.getProductCategory();
                case 2 -> category = ProductCategory.ELECTRONICS.getProductCategory();
                case 3 -> category = ProductCategory.CLOTHING.getProductCategory();
                default -> {
                    System.out.println("Неверный выбор категории. Товар будет добавлен без категории.");
                    category = "Без категории";
                }
            }

            Product product = productService.addProduct(title, price, category);
            log.info("Добавлен продукт: {}", product);
            System.out.println("Добавлен продукт: " + product);
        } catch (Exception e) {
            log.error("Ошибка при добавлении продукта: ", e);
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Отображает все доступные товары.
     */
    private void getAllProducts() {
        try {
            var products = productService.getAll();
            log.info("Все продукты: {}", products);
            System.out.println("Все продукты:");
            products.forEach(System.out::println);
        } catch (Exception e) {
            log.error("Ошибка при получении списка продуктов: ", e);
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Находит товар по ID.
     */
    private void findProductById() {
        try {
            System.out.print("Введите ID товара: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера сканера

            Product product = productService.getProduct(id);
            log.info("Найден продукт с ID {}: {}", id, product);
            System.out.println("Найден продукт: " + product);
        } catch (ProductNotFoundException e) {
            log.error("Ошибка при поиске продукта: ", e);
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            log.error("Непредвиденная ошибка: ", e);
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }

    /**
     * Возвращает в главное меню.
     */
    public void back() {
        cycleProductProgram = false;
        System.out.println("Возврат в главное меню...");
    }
}