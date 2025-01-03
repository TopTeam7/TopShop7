package org.example.Service;

import org.example.Model.Product;
import org.example.Repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Метод addProduct() принимает параметры
     *
     * @param title    типа String
     * @param price    типа int
     * @param category типа String
     *                 Метод создает новый товар
     */
    public Product addProduct(String title, int price, String category) {
        log.info("Добавление нового продукта: title={}, price={}, category={}", title, price, category);
        Product newProduct = new Product(null, title, price, category);
        return productRepository.save(newProduct);
    }

    /**
     * Метод не принимает параметры.
     * Метод возвращает список всех товаров.
     */
    public List<Product> getAll() {
        log.info("Получение всех продуктов");
        return productRepository.findAll();
    }

    /**
     * Метод принимает параметр id
     * Метод возвращает товар, найденный по ID.
     */
    public Product getProduct(int id) {
        log.info("Получение продукта с помощью ID: {}", id);
        return productRepository.findById(id);
    }
}
