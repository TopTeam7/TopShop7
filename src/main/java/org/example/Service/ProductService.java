package org.example.Service;

import org.example.Model.Product;
import org.example.Repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Метод addProduct() примает параметры
     *
     * @param title    типа String
     * @param price    типа int
     * @param category типа String
     *                 Метод создает новый товар
     */
    public Product addProduct(String title, int price, String category) {
        Product newProducts = new Product(null, title, price, category);
        return productRepository.save(newProducts);
    }
    /**
     * Метод не примает параметры.
     * Метод возвращает список всех товаров.
     */
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * Метод примает параметр id
     * Метод возвращает товар, найденный по ID.
     */
    public Product getProduct(int id) {
        return productRepository.productProduct(id);
    }
}
