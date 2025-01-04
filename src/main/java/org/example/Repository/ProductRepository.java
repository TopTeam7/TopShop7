package org.example.Repository;

import org.example.Model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class); // Логгер для логирования событий

    private final Path filePath; // Путь к файлу с продуктами
    private final Path idFilePath; // Путь к файлу с ID продуктов

    public ProductRepository(String filePath, String idFilePath) {
        this.filePath = Path.of(filePath); // Инициализация пути к файлу с продуктами
        this.idFilePath = Path.of(idFilePath); // Инициализация пути к файлу с ID продуктов
    }

    /**
     * Загружает все продукты из файла.
     *
     * @return список продуктов.
     */
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>(); // Создание списка для хранения продуктов
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) { // Открытие файла для чтения
            String filePoducts;
            log.info("Загрузка продуктов из файла: {}", filePath); // Логирование начала загрузки продуктов

            while ((filePoducts = reader.readLine()) != null) { // Чтение файла построчно
                products.add(new Product(filePoducts)); // Создание нового продукта из строки и добавление его в список
            }
        } catch (IOException e) {
            log.warn("Ошибка при чтении файла: ", e); // Логирование ошибки при чтении файла
        }
        return products; // Возврат списка продуктов.
    }

    /**
     * Сохраняет список продуктов в файл.
     *
     * @param products список продуктов.
     */
    public void saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) { // Открытие файла для записи
            log.info("Сохранение продуктов в файл: {}", filePath); // Логирование начала сохранения продуктов
            for (Product product : products) { // Проход по списку продуктов
                writer.write(product.toString()); // Запись продукта в файл
                writer.newLine(); // Переход на новую строку
            }
        } catch (IOException e) {
            log.warn("Ошибка при записи в файл: ", e); // Логирование ошибки при записи в файл
        }
    }

    /**
     * Генерирует новый уникальный ID.
     *
     * @return новый ID.
     */
    public int generateNewId() {
        int lastId = 0; // Инициализация переменной для хранения последнего ID
        try (BufferedReader reader = new BufferedReader(new FileReader(idFilePath.toFile()))) { // Открытие файла для чтения
            String fileProduct = reader.readLine(); // Чтение первой строки файла
            if (fileProduct != null) {
                lastId = Integer.parseInt(fileProduct); // Преобразование строки в целое число
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении файла idProducts_id.txt: ", e); // Логирование ошибки при чтении файла
        }
        lastId++; // Увеличение последнего ID на 1

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFilePath.toFile()))) { // Открытие файла для записи
            writer.write(String.valueOf(lastId)); // Запись нового ID в файл
        } catch (IOException e) {
            log.error("Ошибка при записи в файла idProducts_id.txt : ", e); // Логирование ошибки при записи в файл
        }

        return lastId; // Возврат нового ID
    }

    /**
     * Сохраняет продукт в файл.
     *
     * @param product продукт для сохранения.
     * @return сохраненный продукт.
     */
    public Product save(Product product) {
        List<Product> products = loadProducts(); // Загрузка всех продуктов из файла
        product.setId(generateNewId()); // Генерация нового ID для продукта
        products.add(product); // Добавление нового продукта в список
        saveProducts(products); // Сохранение списка продуктов в файл
        return product; // Возврат сохраненного продукта
    }

    /**
     * Находит продукт по ID.
     *
     * @param id ID продукта.
     * @return найденный продукт.
     */
    public Product findById(int id) {
        List<Product> products = loadProducts(); // Загрузка всех продуктов из файла
        return products.stream() // Поиск продукта по ID
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null); // Возврат найденного продукта или null, если продукт не найден
    }

    /**
     * Возвращает все продукты.
     *
     * @return список всех продуктов.
     */
    public List<Product> findAll() {
        return loadProducts(); // Загрузка всех продуктов из файла
    }
}
