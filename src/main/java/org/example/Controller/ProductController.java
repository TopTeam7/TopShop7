package org.example.Controller;

import org.example.Model.ProductCategory;
import org.example.OrderException.ProductNotFoundException;
import org.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private boolean cycleProductProgram = true;

    Scanner sc = new Scanner(System.in);
    private String productTitle;
    private int productPrice;
    private String productCategory;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Метод принимает параметры
     *
     * @param isCycleProgram типа boolean
     *                       Метод запускает взаимодействие с Product.
     */
    public void startProduct(boolean isCycleProgram) {
        cycleProductProgram = isCycleProgram;
        while (cycleProductProgram) {
            int choice;
            System.out.println("===== Управление товарами =====");
            System.out.println("1. Добавить товар :");
            System.out.println("2. Посмотреть все доступные товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("0. Назад ");
            choice = sc.nextInt();
            sc.nextLine();
            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> getProduct();
                    case 3 -> findProduct();
                    case 0 -> System.out.println("Назад");
                    default -> log.error("Товар не найден");
                }
            } catch (ProductNotFoundException e) {
                log.error("Ошибка: ", e);
            }
        }
    }

    /**
     * Метод не принимает параметры
     * Метод addProduct() инициализирует поля класса Product
     * и добавляет его в список.
     */
    private void addProduct() {
        int categoryNum = 0;
        System.out.print("Введите название товара - ");
        productTitle = sc.nextLine();

        System.out.print("Введите цену товара - ");
        productPrice = sc.nextInt();
        sc.nextLine();

        System.out.println("Выберите категорию товара:");

        System.out.println("1. " + ProductCategory.FOOD.getProductCategory());
        System.out.println("2. " + ProductCategory.ELECTRONICS.getProductCategory());
        System.out.println("3. " + ProductCategory.CLOTHING.getProductCategory());
        categoryNum = sc.nextInt();

        switch (categoryNum) {
            case 1 -> productCategory = ProductCategory.FOOD.getProductCategory();
            case 2 -> productCategory = ProductCategory.ELECTRONICS.getProductCategory();
            case 3 -> productCategory = ProductCategory.CLOTHING.getProductCategory();
            default -> productCategory = "Товар не найден";
        }
        try {
            String info = productService.addProduct(productTitle, productPrice, productCategory).toString();
            log.info("Добавленный продукт: {}", info);
            System.out.println(info);
        } catch (IllegalArgumentException | ProductNotFoundException e) {
            log.error("Ошибка: ", e);
        }
    }

    /**
     * Метод не принимает параметры
     * Метод getProduct() показывает все доступные товары.
     */
    private void getProduct() {
        String product = productService.getAll().toString();
        log.info("Все продукты: {}", product);
        System.out.println(product);
    }

    /**
     * Метод не принимает параметры
     * Метод findProduct() для поиска товара по ID.
     */
    private void findProduct() {
        Integer findID;

        System.out.println("Поиск товара по ID  ");
        findID = sc.nextInt();
        sc.nextLine();

        String info = productService.getProduct(findID).toString();
        log.info("Продукт, найденный ID {}: {}", findID, info);
        System.out.println(info);
    }
}
