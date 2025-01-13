package org.example.Service;

import org.example.Model.Product;
import org.example.Repository.ProductRepository;
import org.example.OrderException.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductService {
    // Логгер для логирования событий
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    // Репозиторий для работы с продуктами
    private final ProductRepository productRepository;

    // Конструктор класса ProductService
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /* Метод для добавления нового продукта
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

    /* Метод для получения всех продуктов
     *
     * @return список всех продуктов
     */
    public List<Product> getAll() {
        log.info("Получение всех продуктов");
        return productRepository.findAll(); // Получение всех продуктов из репозитория
    }

    /**
     * Метод для получения продукта по ID
     *
     * @param id ID продукта
     * @return найденный продукт
     * @throws ProductNotFoundException если продукт не найден
     */
    public Product getProduct(int id) {
        log.info("Получение продукта с помощью ID: {}", id);
        Product product = productRepository.findById(id); // Получение продукта по ID из репозитория
        if (product == null) {
            throw new ProductNotFoundException("Продукт с ID " + id + " не найден");
        }
        return product;
    }
}