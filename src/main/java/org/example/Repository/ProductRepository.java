
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

public class ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);


    private final Path filePath;
    private final Path idFilePath;


    public ProductRepository(String filePath, String idFilePath) {
        this.filePath = Path.of(filePath); // Инициализация пути к файлу продуктов
        this.idFilePath = Path.of(idFilePath); // Инициализация пути к файлу ID

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

    /* Загружает все продукты из файла.
     *
             * @return список продуктов.
            */
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>(); // Создание списка для хранения продуктов
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String filePoducts;
            log.info("Загрузка продуктов из файла: {}", filePath);
            while ((filePoducts = reader.readLine()) != null) {
                products.add(new Product(filePoducts)); // Используем конструктор из строки
            }
        } catch (IOException e) {
            log.warn("Ошибка при чтении файла: ", e);
        }
        return products; // Возврат списка продуктов
    }

    /* Сохраняет список продуктов в файл.
     *
             * @param products список продуктов.
     */
    public void saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            log.info("Сохранение продуктов в файл: {}", filePath);
            for (Product product : products) {
                writer.write(product.toString()); // Используем toString() для сохранения
                writer.newLine(); // Переход на новую строку
            }
        } catch (IOException e) {
            log.warn("Ошибка при записи в файл: ", e);
        }
    }


            /* Генерирует новый уникальный ID.
            *
            * @return новый ID.
            */
    public int generateNewId() {
        int lastId = 0; // Переменная для хранения последнего ID
        try (BufferedReader reader = new BufferedReader(new FileReader(idFilePath.toFile()))) {
            String fileProduct = reader.readLine(); // Чтение строки из файла
            if (fileProduct != null) {
                lastId = Integer.parseInt(fileProduct); // Преобразование строки в целое число
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении файла idProducts_id.txt: ", e);
        }
        lastId++;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFilePath.toFile()))) {
            writer.write(String.valueOf(lastId)); // Запись нового ID в файл
        } catch (IOException e) {
            log.error("Ошибка при записи в файла idProducts_id.txt : ", e);
        }

        return lastId; // Возврат нового ID
    }

    /* Сохраняет продукт в файл.
            *
            * @param product продукт для сохранения.
            * @return сохраненный продукт.
            */
    public Product save(Product product) {
        List<Product> products = loadProducts(); // Загрузка всех продуктов
        product.setId(generateNewId()); // Генерация нового ID для продукта
        products.add(product); // Добавление продукта в список
        saveProducts(products); // Сохранение списка продуктов в файл
        return product; // Возврат сохраненного продукта
    }

    /* Находит продукт по ID.
            *
            * @param id ID продукта.
     * @return найденный продукт.
            * @throws ProductNotFoundException если продукт не найден.
     */
    public Product findById(int id) {
        List<Product> products = loadProducts(); // Загрузка всех продуктов
        return products.stream() // Поиск продукта по ID
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Продукт с ID " + id + " не найден")); // Выброс исключения, если продукт не найден
    }

    /* Возвращает все продукты.
     *
             * @return список всех продуктов.
     */
    public List<Product> findAll() {
        return loadProducts(); // Загрузка и возврат всех продуктов
    }
}