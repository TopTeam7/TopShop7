package org.example.Controller;

import org.example.OrderException.ProductNotFoundException;
import org.example.Service.ProductService;

import java.util.Scanner;

public class ProductController {
    private final ProductService productService;
    private boolean cyclePoductProgram = true;

    Scanner sc = new Scanner(System.in);
    private String productTitle;
    private Integer productPrice;
    private String productCategory;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Метод примает параметры
     *
     * @param isCycleProgram типа boolean
     *                       Метод запускает взаимодействие с Product.
     */
    public void startProduct(boolean isCycleProgram) {
        cyclePoductProgram = isCycleProgram;
        while (cyclePoductProgram) {
            int choise;
            System.out.println("===== Управление товарами =====");
            System.out.println("1. Добавить товар :");
            System.out.println("2. Посмотреть все доступные товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("0. Назад ");
            choise = sc.nextInt();
            sc.nextLine();
            try {
                switch (choise) {
                    case 1 -> addProduct();
                    case 2 -> getProduct();
                    case 3 -> findProduct();
                    case 4 -> System.out.println("Назад");
                    default -> System.out.println("Товар не найден");
                }
            } catch (ProductNotFoundException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Метод не примает параметры
     * Метод addProduct() инициализирует поля класса Product
     * и добавляет его в список.
     */
    private void addProduct() {
        int categoryNumber = 0;
        System.out.print("Введите название товара - ");
        productTitle = sc.nextLine();

        System.out.print("Введите цену товара - ");
        productPrice = sc.nextInt();
        sc.nextLine();

        System.out.println(" Выберите категорию товара:");

        System.out.println(" 1)Продукты");
        System.out.println(" 2)Электроника");
        System.out.println(" 3)Одежда");
        categoryNumber = sc.nextInt();

        switch (categoryNumber) {
            case 1 -> productCategory = "Фрукты";
            case 2 -> productCategory = "Смартфоны";
            case 3 -> productCategory = "Джинсовая одежда ";
            default -> productCategory = "Товар не найден";
        }
        String info = productService.addProduct(productTitle, productPrice, productCategory).toString();
        System.out.println(info);
    }

    /**
     * Метод не примает параметры
     * Метод getProduct() показывает все доступные товары.
     */
    private void getProduct() {

        String product = productService.getAll().toString();
        System.out.println(product);
    }

    /**
     * Метод не примает параметры
     * Метод findProduct() для поиска товара по ID.
     */
    private void findProduct() {
        Integer findID;
        System.out.println("Поиск товара по ID  ");
        findID = sc.nextInt();
        sc.nextLine();
        String info = productService.getProduct(findID).toString();
        System.out.println(info);


    }
}
