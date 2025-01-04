package org.example.Service;

import org.example.Model.Product;
import org.example.Repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class); // Логгер для логирования событий
    private final ProductRepository productRepository; // Репозиторий для управления продуктами

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository; // Инициализация репозитория
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
        log.info("Добавление нового продукта: title={}, price={}, category={}", title, price, category); // Логирование начала добавления нового продукта
        Product newProduct = new Product(null, title, price, category); // Создание нового продукта
        return productRepository.save(newProduct); // Сохранение продукта в репозитории
    }

    /**
     * Метод не принимает параметры.
     * Метод возвращает список всех товаров.
     */
    public List<Product> getAll() {
        log.info("Получение всех продуктов"); // Логирование начала получения всех продуктов
        return productRepository.findAll(); // Получение всех продуктов из репозитория
    }

    /**
     * Метод принимает параметр id
     * Метод возвращает товар, найденный по ID.
     */
    public Product getProduct(int id) {
        log.info("Получение продукта с помощью ID: {}", id); // Логирование начала получения продукта по ID
        return productRepository.findById(id); // Получение продукта по ID из репозитория
    }
}
