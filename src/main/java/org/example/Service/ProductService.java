package org.example.Service;

import org.example.Model.Product;
import org.example.Repository.ProductRepository;
import org.example.OrderException.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Сервис для работы с продуктами.
 */
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    /**
     * Конструктор для создания ProductService.
     *
     * @param productRepository репозиторий для работы с продуктами
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Добавляет новый продукт.
     *
     * @param title    название продукта
     * @param price    цена продукта
     * @param category категория продукта
     * @return добавленный продукт
     */
    public Product addProduct(String title, int price, String category) {
        log.info("Добавление нового продукта: title={}, price={}, category={}", title, price, category);
        Product newProduct = new Product(null, title, price, category); // Создание нового продукта
        return productRepository.save(newProduct); // Сохранение продукта в репозитории
    }

    /**
     * Возвращает список всех продуктов.
     *
     * @return список всех продуктов
     */
    public List<Product> getAll() {
        log.info("Получение всех продуктов");
        return productRepository.findAll(); // Получение всех продуктов из репозитория
    }

    /**
     * Находит продукт по ID.
     *
     * @param id ID продукта
     * @return найденный продукт
     * @throws ProductNotFoundException если продукт не найден
     */
    public Product getProduct(int id) throws ProductNotFoundException {
        log.info("Получение продукта по ID: {}", id);
        return productRepository.findById(id); // Получение продукта по ID из репозитория
    }
}