package org.example.Model;

import java.util.Arrays;

/**
 * Перечисление, представляющее категории продуктов.
 */
public enum ProductCategory {
    /** Одежда */
    CLOTHING("Одежда"),
    /** Электроника */
    ELECTRONICS("Электроника"),
    /** Продукты */
    FOOD("Продукты");

    private final String productCategory;

    /**
     * Конструктор для создания категории продукта.
     *
     * @param productCategory название категории продукта
     */
    ProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    /**
     * Возвращает название категории продукта.
     *
     * @return название категории продукта
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     * Возвращает категорию продукта по заданному индексу.
     *
     * @param selection индекс категории (начиная с 1)
     * @return категория продукта
     * @throws IllegalArgumentException если индекс не найден
     */
    public static ProductCategory getProducts(int selection) {
        return Arrays.stream(values())
                .filter(c -> c.ordinal() + 1 == selection)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует попробуйте еще раз"));
    }
}
