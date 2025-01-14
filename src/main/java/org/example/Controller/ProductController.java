
package org.example.Controller;

import org.example.Model.ProductCategory;
import org.example.OrderException.ProductNotFoundException;
import org.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ProductController {
    // Логгер для логирования событий
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    // Сервис для работы с продуктами
    private final ProductService productService;

    // Флаг для управления циклом программы
    private boolean cycleProductProgram = true;

    // Сканер для ввода данных с консоли
    private Scanner sc = new Scanner(System.in);

    // Поля для хранения данных о продукте
    private String productTitle;
    private int productPrice;
    private String productCategory;

    // Конструктор класса ProductController
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /** Метод для запуска программы управления товарами
     *
     * @param isCycleProgram флаг для управления циклом программы
     */
    public void startProduct(boolean isCycleProgram) {
        cycleProductProgram = isCycleProgram;
        while (cycleProductProgram) {
            int choice;
            // Вывод меню на экран
            System.out.println("===== Управление товарами =====");
            System.out.println("1. Добавить товар :");
            System.out.println("2. Посмотреть все доступные товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("0. Назад ");
            choice = sc.nextInt();
            sc.nextLine(); // Очистка буфера сканера
            try {
                // Обработка выбора пользователя
                switch (choice) {
                    case 1 -> addProduct(); // Вызов метода для добавления продукта
                    case 2 -> getProduct(); // Вызов метода для отображения всех продуктов
                    case 3 -> findProduct(); // Вызов метода для поиска продукта по ID
                    case 0 -> back(); // Вызов метода для возврата в главное меню
                    default -> log.error("Неверный выбор"); // Логирование ошибки при неверном выборе
                }
            } catch (ProductNotFoundException e) {
                log.error("Ошибка: ", e); // Логирование ошибки, если продукт не найден
            }
        }
    }

    /* Метод для добавления нового продукта
     */
    private void addProduct() {
        int categoryNum = 0;
        // Ввод названия товара
        System.out.print("Введите название товара - ");
        productTitle = sc.nextLine();
        // Ввод цены товара
        System.out.print("Введите цену товара - ");
        productPrice = sc.nextInt();
        sc.nextLine(); // Очистка буфера сканера
        // Выбор категории товара
        System.out.println("Выберите категорию товара:");
        System.out.println("1. " + ProductCategory.FOOD.getProductCategory());
        System.out.println("2. " + ProductCategory.ELECTRONICS.getProductCategory());
        System.out.println("3. " + ProductCategory.CLOTHING.getProductCategory());
        categoryNum = sc.nextInt();

        // Определение категории товара
        switch (categoryNum) {
            case 1 -> productCategory = ProductCategory.FOOD.getProductCategory();
            case 2 -> productCategory = ProductCategory.ELECTRONICS.getProductCategory();
            case 3 -> productCategory = ProductCategory.CLOTHING.getProductCategory();
            default -> productCategory = "Товар не найден";
        }
        try {
            // Добавление продукта и логирование результата
            String info = productService.addProduct(productTitle, productPrice, productCategory).toString();
            log.info("Добавленный продукт: {}", info);
            System.out.println(info);
        } catch (IllegalArgumentException | ProductNotFoundException e) {
            log.error("Ошибка: ", e); // Логирование ошибки, если продукт не найден
        }
    }


    /** Метод для отображения всех доступных товаров
     */
    private void getProduct() {
        String product = productService.getAll().toString(); // Получение всех продуктов
        log.info("Все продукты: {}", product); // Логирование всех продуктов
        System.out.println(product); // Вывод всех продуктов на экран
    }

    /** Метод для поиска товара по ID
     */
    private void findProduct() {
        Integer findID;
        System.out.println("Поиск товара по ID  ");
        findID = sc.nextInt(); // Считывание ID продукта
        sc.nextLine(); // Очистка буфера сканера
        String info = productService.getProduct(findID).toString(); // Получение продукта по ID
        log.info("Продукт, найденный ID {}: {}", findID, info); // Логирование найденного продукта
        System.out.println(info); // Вывод найденного продукта на экран
    }

    /**
     * Метод для возврата в главное меню
     */
    public void back() {
        cycleProductProgram = false; // Установка флага цикла в false для выхода из цикла
    }
}