package org.example.Repository;

import org.example.Model.Product;
import org.example.OrderException.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с хранилищем продуктов.
 */
public class ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    private final Path filePath; // Путь к файлу с продуктами
    private final Path idFilePath; // Путь к файлу с последним ID

    /**
     * Конструктор для создания ProductRepository.
     *
     * @param filePath    путь к файлу с продуктами
     * @param idFilePath  путь к файлу с последним ID
     */
    public ProductRepository(String filePath, String idFilePath) {
        this.filePath = Path.of(filePath);
        this.idFilePath = Path.of(idFilePath);

        // Проверка и создание файлов, если они не существуют
        try {
            if (Files.notExists(this.filePath)) {
                Files.createFile(this.filePath);
                log.info("Файл продуктов создан: {}", this.filePath);
            }
            if (Files.notExists(this.idFilePath)) {
                Files.createFile(this.idFilePath);
                log.info("Файл ID создан: {}", this.idFilePath);
            }
        } catch (IOException e) {
            log.error("Ошибка при создании файлов: ", e);
        }
    }

    /**
     * Загружает все продукты из файла.
     *
     * @return список продуктов
     */
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            log.info("Загрузка продуктов из файла: {}", filePath);
            String line;
            while ((line = reader.readLine()) != null) {
                products.add(new Product(line)); // Используем конструктор из строки
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении файла: ", e);
        }
        return products;
    }

    /**
     * Сохраняет список продуктов в файл.
     *
     * @param products список продуктов
     */
    public void saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            log.info("Сохранение продуктов в файл: {}", filePath);
            for (Product product : products) {
                writer.write(product.toString()); // Используем toString() для сохранения
                writer.newLine(); // Переход на новую строку
            }
        } catch (IOException e) {
            log.error("Ошибка при записи в файл: ", e);
        }
    }

    /**
     * Генерирует новый уникальный ID.
     *
     * @return новый ID
     */
    public int generateNewId() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(idFilePath.toFile()))) {
            String line = reader.readLine();
            if (line != null) {
                lastId = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            log.error("Ошибка при чтении файла ID: ", e);
        }

        int newId = lastId + 1;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFilePath.toFile()))) {
            writer.write(String.valueOf(newId));
        } catch (IOException e) {
            log.error("Ошибка при записи в файл ID: ", e);
        }

        return newId;
    }

    /**
     * Сохраняет продукт в файл.
     *
     * @param product продукт для сохранения
     * @return сохраненный продукт
     */
    public Product save(Product product) {
        List<Product> products = loadProducts();
        product.setId(generateNewId()); // Генерация нового ID для продукта
        products.add(product); // Добавление продукта в список
        saveProducts(products); // Сохранение списка продуктов в файл
        return product;
    }

    /**
     * Находит продукт по ID.
     *
     * @param id ID продукта
     * @return найденный продукт
     * @throws ProductNotFoundException если продукт не найден
     */
    public Product findById(int id) throws ProductNotFoundException {
        List<Product> products = loadProducts();
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Продукт с ID " + id + " не найден"));
    }

    /**
     * Возвращает все продукты.
     *
     * @return список всех продуктов
     */
    public List<Product> findAll() {
        return loadProducts();
    }
}