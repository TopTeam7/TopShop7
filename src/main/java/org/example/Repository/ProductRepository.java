package org.example.Repository;

import org.example.Model.Product;
import org.example.OrderException.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<Product> products;
    private int countID;

    public ProductRepository() {
        this.products = new ArrayList<>();
        countID = 0;
    }

    /**
     * Метод примает параметры
     *
     * @param product Метод  save() сохраняет товар в repository.
     */
    public Product save(Product product) {
        try {
            product.setId(++countID);
            if (products.add(product)) {
                return product;
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            throw new ProductNotFoundException("Товар не найден");
        }
    }

    /**
     * Метод не примает параметры
     * Метод findAll() возвращает список всех товаров Product.
     */
    public List<Product> findAll() {
        return products;
    }

    /**
     * Метод примает параметры
     *
     * @param id типа int
     *           Метод productProduct() возвращает товар по ID.
     */
    public Product productProduct(int id) {
        try {
            return products.stream()
                    .filter(products -> products != null && products.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (IndexOutOfBoundsException e) {
            throw new ProductNotFoundException("Товар не найден");
        }
    }
}
