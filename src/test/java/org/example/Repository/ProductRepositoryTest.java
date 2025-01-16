package org.example.Repository;

import org.example.Model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для {@link ProductRepository}.
 */
class ProductRepositoryTest {

    private ProductRepository productRepository;
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
    }

    /**
     * Очистка после каждого теста.
     *
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @AfterEach
    void tearDown() throws IOException {
        // Удаление временных файлов после тестирования
        Files.deleteIfExists(tempProductFile);
        Files.deleteIfExists(tempIdFile);
    }

    /**
     * Тестирование загрузки продуктов из файла.
     */
    @Test
    void loadProducts() {
        List<Product> products = productRepository.loadProducts();
        assertTrue(products.isEmpty(), "Список продуктов должен быть пустым");
    }

    /**
     * Тестирование сохранения продуктов в файл.
     */
    @Test
    void saveProducts() {
        Product product = new Product(1, "Test Product", 100, "Test Category");
        productRepository.saveProducts(List.of(product));
        List<Product> loadedProducts = productRepository.loadProducts();
        assertEquals(1, loadedProducts.size(), "Должен быть один продукт");
        assertEquals("Test Product", loadedProducts.get(0).getTitle(), "Название продукта должно совпадать");
    }

    /**
     * Тестирование генерации нового ID.
     */
    @Test
    void generateNewId() {
        int newId = productRepository.generateNewId();
        assertEquals(1, newId, "Первый сгенерированный ID должен быть 1");
        int anotherId = productRepository.generateNewId();
        assertEquals(2, anotherId, "Второй сгенерированный ID должен быть 2");
    }

    /**
     * Тестирование сохранения нового продукта.
     */
    @Test
    void save() {
        Product product = new Product(null, "New Product", 200, "New Category");
        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId(), "ID продукта не должен быть null");
        assertEquals("New Product", savedProduct.getTitle(), "Название продукта должно совпадать");
    }

    /**
     * Тестирование поиска продукта по ID.
     */
    @Test
    void findById() {
        Product product = new Product(null, "Find Product", 300, "Find Category");
        productRepository.save(product);
        Product foundProduct = productRepository.findById(product.getId());
        assertEquals("Find Product", foundProduct.getTitle(), "Название продукта должно совпадать");
    }

    /**
     * Тестирование получения всех продуктов.
     */
    @Test
    void findAll() {
        Product product1 = new Product(null, "Product 1", 400, "Category 1");
        Product product2 = new Product(null, "Product 2", 500, "Category 2");
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> allProducts = productRepository.findAll();
        assertEquals(2, allProducts.size(), "Должно быть два продукта");
    }
}