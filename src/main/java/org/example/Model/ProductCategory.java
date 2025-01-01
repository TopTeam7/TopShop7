package org.example.Model;

import java.util.Arrays;


public enum ProductCategory {
    CLOTHING("Одежда"),
    ELECTRONICS("Электроника"),
    FOOD("Продукты");
    private final String productCategory;

    ProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public static ProductCategory getProducts(int selection) {
        return Arrays.stream(values())
                .filter(c -> c.ordinal() + 1 == selection)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует попробуйте еще раз"));
    }
}
