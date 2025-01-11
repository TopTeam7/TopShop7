package org.example.Repository;

import org.example.Model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с хранилищем покупателей.
 */
public class CustomerRepository {
    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);
    private static final String CUSTOMERS_FILE = "src/main/resources/customers.txt";
    private static final String LAST_ID_FILE = "src/main/resources/last_id.txt";

    /**
     * Загружает последний использованный ID из файла.
     *
     * @return последний использованный ID
     */
    private int loadLastId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LAST_ID_FILE))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException e) {
            log.error("Ошибка при чтении last_id.txt", e);
            return 0;
        }
    }

    /**
     * Сохраняет последний использованный ID в файл.
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
     * Загружает список всех покупателей из файла.
     *
     * @return список покупателей
     */
    public List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(new Customer(line));
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении customers.txt", e);
        }
        return customers;
    }

    /**
     * Сохраняет список покупателей в файл.
     *
     * @param customers список покупателей
     */
    public void saveCustomers(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("Ошибка при записи customers.txt", e);
        }
    }

    /**
     * Добавляет нового покупателя в хранилище.
     *
     * @param customer объект покупателя
     */
    public void addCustomer(Customer customer) {
        List<Customer> customers = loadCustomers();
        int lastId = loadLastId();
        customer = new Customer(lastId + 1, customer.getName(), customer.getType()); // Генерация нового ID
        customers.add(customer);
        saveCustomers(customers);
        saveLastId(lastId + 1); // Сохраняем новый ID
        log.info("Добавлен новый покупатель: {}", customer);
    }
}