package org.example.Model;

import java.util.Arrays;

public enum ProductCategory {
    CLOTHING("Одежда"), // Категория "Одежда"
    ELECTRONICS("Электроника"), // Категория "Электроника"
    FOOD("Продукты"); // Категория "Продукты"

    private final String productCategory; // Название категории продукта

    // Конструктор для инициализации названия категории
    ProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    // Геттер для названия категории продукта
    public String getProductCategory() {
        return productCategory;
    }

    // Метод для получения категории продукта по номеру выбора
    public static ProductCategory getProducts(int selection) {
        return Arrays.stream(values()) // Преобразование массива значений перечисления в поток
                .filter(c -> c.ordinal() + 1 == selection) // Фильтрация по номеру выбора
                .findFirst() // Нахождение первого совпадения
                .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует попробуйте еще раз")); // Выброс исключения, если категория не найдена
    }
}
