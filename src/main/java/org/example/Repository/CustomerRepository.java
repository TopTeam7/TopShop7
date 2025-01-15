package org.example.Repository;

import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с хранилищем покупателей.
 * Обеспечивает загрузку, сохранение, добавление, удаление и обновление данных о покупателях.
 * Данные хранятся в текстовых файлах: customers.txt (список покупателей) и last_id.txt (последний использованный ID).
 */
public class CustomerRepository {
    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);
    private final String CUSTOMERS_FILE;
    private final String LAST_ID_FILE;
    public CustomerRepository(){
        this.CUSTOMERS_FILE = "src/main/resources/customers.txt";
        this.LAST_ID_FILE = "src/main/resources/last_id.txt";
    }
    public CustomerRepository(String path, String pathId){
        this.CUSTOMERS_FILE = path;
        this.LAST_ID_FILE = pathId;
        }

    /**
     * Загружает последний использованный ID из файла last_id.txt.
     * Если файл не существует, создает его и возвращает 0.
     *
     * @return последний использованный ID
     */
    private int loadLastId() {
        File file = new File(LAST_ID_FILE);
        if (!file.exists()) {
            log.warn("Файл last_id.txt не найден. Будет создан новый файл.");
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException | NumberFormatException e) {
            log.error("Ошибка при чтении last_id.txt", e);
            return 0;
        }
    }

    /**
     * Сохраняет последний использованный ID в файл last_id.txt.
     *
     * @param id последний использованный ID
     */
    private void saveLastId(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            log.error("Ошибка при записи last_id.txt", e);
        }
    }

    /**
     * Загружает список всех покупателей из файла customers.txt.
     * Если файл не существует, создает его и возвращает пустой список.
     *
     * @return список покупателей
     */
    public List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        File file = new File(CUSTOMERS_FILE);

        if (!file.exists()) {
            log.warn("Файл customers.txt не найден. Будет создан новый файл.");
            return customers;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(";");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        CustomerType type = CustomerType.valueOf(parts[2]);
                        customers.add(new Customer(id, name, type));
                    } else {
                        log.error("Некорректный формат строки: " + line);
                    }
                } catch (Exception e) {
                    log.error("Ошибка при чтении строки: " + line, e);
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении customers.txt", e);
        }
        return customers;
    }

    /**
     * Сохраняет список покупателей в файл customers.txt.
     * Перед записью создает резервную копию файла.
     *
     * @param customers список покупателей
     */
    public void saveCustomers(List<Customer> customers) {
        File file = new File(CUSTOMERS_FILE);
        File backupFile = new File(CUSTOMERS_FILE + ".bak");

        // Создаем резервную копию
        if (file.exists()) {
            file.renameTo(backupFile);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
            // Удаляем резервную копию после успешной записи
            if (backupFile.exists()) {
                backupFile.delete();
            }
        } catch (IOException e) {
            log.error("Ошибка при записи customers.txt", e);
            // Восстанавливаем резервную копию
            if (backupFile.exists()) {
                backupFile.renameTo(file);
            }
        }
    }

    /**
     * Добавляет нового покупателя в хранилище.
     * Автоматически генерирует новый ID и сохраняет его в last_id.txt.
     *
     * @param customer объект покупателя
     */
    public void addCustomer(Customer customer) {
        List<Customer> customers = loadCustomers();
        int lastId = loadLastId();
        int newId = lastId + 1;
        Customer newCustomer = new Customer(newId, customer.getName(), customer.getType());
        customers.add(newCustomer);
        saveCustomers(customers);
        saveLastId(newId);
        log.info("Добавлен новый покупатель: {}", newCustomer);
    }

    /**
     * Удаляет покупателя по ID.
     *
     * @param id идентификатор покупателя
     */
    public void deleteCustomer(int id) {
        List<Customer> customers = loadCustomers();
        boolean removed = customers.removeIf(customer -> customer.getId() == id);
        if (removed) {
            saveCustomers(customers);
            log.info("Покупатель с ID {} удален.", id);
        } else {
            log.warn("Покупатель с ID {} не найден.", id);
        }
    }

    /**
     * Обновляет данные покупателя.
     *
     * @param updatedCustomer объект покупателя с обновленными данными
     */
    public void updateCustomer(Customer updatedCustomer) {
        List<Customer> customers = loadCustomers();
        boolean found = false;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == updatedCustomer.getId()) {
                customers.set(i, updatedCustomer);
                found = true;
                break;
            }
        }
        if (!found) {
            log.warn("Покупатель с ID {} не найден.", updatedCustomer.getId());
        } else {
            saveCustomers(customers);
            log.info("Данные покупателя с ID {} обновлены.", updatedCustomer.getId());
        }
    }
}