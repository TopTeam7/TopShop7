package org.example.Repository;

import org.example.Model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    private final Path filePath;
    private final Path idFilePath;

    public ProductRepository(String filePath, String idFilePath) {
        this.filePath = Path.of(filePath);
        this.idFilePath = Path.of(idFilePath);
    }

    /**
     * Загружает все продукты из файла.
     *
     * @return список продуктов.
     */
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String filePoducts;
            log.info("Загрузка продуктов из файла: {}", filePath);

            while ((filePoducts = reader.readLine()) != null) {
                products.add(new Product(filePoducts)); // Используем конструктор из строки
            }
        } catch (IOException e) {
            log.warn("Ошибка при чтении файла: ", e);
        }
        return products;
    }

    /**
     * Сохраняет список продуктов в файл.
     *
     * @param products список продуктов.
     */
    public void saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            log.info("Сохранение продуктов в файл: {}", filePath);
            for (Product product : products) {
                writer.write(product.toString()); // Используем toString() для сохранения
                writer.newLine();
            }
        } catch (IOException e) {
            log.warn("Ошибка при записи в файл: ", e);
        }
    }

    /**
     * Генерирует новый уникальный ID.
     *
     * @return новый ID.
     */
    public int generateNewId() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(idFilePath.toFile()))) {
            String fileProduct = reader.readLine();
            if (fileProduct != null) {
                lastId = Integer.parseInt(fileProduct);
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении файла idProducts_id.txt: ", e);
        }
        lastId++;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFilePath.toFile()))) {
            writer.write(String.valueOf(lastId));
        } catch (IOException e) {
            log.error("Ошибка при записи в файла idProducts_id.txt : ", e);
        }

        return lastId;
    }

    /**
     * Сохраняет продукт в файл.
     *
     * @param product продукт для сохранения.
     * @return сохраненный продукт.
     */
    public Product save(Product product) {
        List<Product> products = loadProducts();
        product.setId(generateNewId());
        products.add(product);
        saveProducts(products);
        return product;
    }

    /**
     * Находит продукт по ID.
     *
     * @param id ID продукта.
     * @return найденный продукт.
     */
    public Product findById(int id) {
        List<Product> products = loadProducts();
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает все продукты.
     *
     * @return список всех продуктов.
     */
    public List<Product> findAll() {
        return loadProducts();
    }
}

