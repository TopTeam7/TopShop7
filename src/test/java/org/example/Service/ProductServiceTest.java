package org.example.Service;

import org.example.Model.Product;
import org.example.Repository.ProductRepository;
import org.example.OrderException.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для {@link ProductService}.
 */
class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;
    private Path tempProductFile;
    private Path tempIdFile;

    /**
     * Настройка перед каждым тестом.
     *
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @BeforeEach
    void setUp() throws IOException {
        // Создание временных файлов для тестирования
        tempProductFile = Files.createTempFile("testProducts", ".txt");
        tempIdFile = Files.createTempFile("testId", ".txt");
        productRepository = new ProductRepository(tempProductFile.toString(), tempIdFile.toString());
        productService = new ProductService(productRepository);
    }

    /**
     * Тестирование добавления нового продукта.
     */
    @Test
    void addProduct() {
        // Тестирование добавления нового продукта
        Product addedProduct = productService.addProduct("New Product", 200, "New Category");
        assertNotNull(addedProduct.getId(), "ID продукта не должен быть null");
        assertEquals("New Product", addedProduct.getTitle(), "Название продукта должно совпадать");
    }

    /**
     * Тестирование получения всех продуктов.
     */
    @Test
    void getAll() {
        // Тестирование получения всех продуктов
        productService.addProduct("Product 1", 400, "Category 1");
        productService.addProduct("Product 2", 500, "Category 2");
        List<Product> allProducts = productService.getAll();
        assertEquals(2, allProducts.size(), "Должно быть два продукта");
    }

    /**
     * Тестирование получения продукта по ID.
     */
    @Test
    void getProduct() {
        // Тестирование получения продукта по ID
        Product addedProduct = productService.addProduct("Find Product", 300, "Find Category");
        Product foundProduct = productService.getProduct(addedProduct.getId());
        assertEquals("Find Product", foundProduct.getTitle(), "Название продукта должно совпадать");
    }

    /**
     * Тестирование получения продукта по несуществующему ID.
     */
    @Test
    void getProduct_ProductNotFound() {
        // Тестирование получения продукта по несуществующему ID
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(999));
    }
}
