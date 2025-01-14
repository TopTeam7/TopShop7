
        package org.example.Repository;

import org.example.Model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Класс для работы с хранилищем покупателей.
        */
public class CustomerRepository {
    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);
    private static final String CUSTOMERS_FILE = "src/main/resources/customers.txt";
    private static final String LAST_ID_FILE = "src/main/resources/last_id.txt";

    /** Загружает последний использованный ID из файла.
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

    /** Сохраняет последний использованный ID в файл.
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

    /** Загружает список всех покупателей из файла.
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
                    customers.add(new Customer(line));
                } catch (Exception e) {
                    log.error("Ошибка при чтении строки: " + line, e);
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении customers.txt", e);
        }
        return customers;
    }

    /** Сохраняет список покупателей в файл.
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

    /** Добавляет нового покупателя в хранилище.
     *
             * @param customer объект покупателя
     */
    public void addCustomer(Customer customer) {
        List<Customer> customers = loadCustomers();
        int lastId = loadLastId();
        customer = new Customer(lastId + 1, customer.getName(), customer.getType());
        customers.add(customer);
        saveCustomers(customers);
        saveLastId(lastId + 1);
        log.info("Добавлен новый покупатель: {}", customer);
    }

    /**
     * Удаляет покупателя по ID.
     *
     * @param id идентификатор покупателя
     */
    public void deleteCustomer(int id) {
        List<Customer> customers = loadCustomers();
        customers.removeIf(customer -> customer.getId() == id);
        saveCustomers(customers);
        log.info("Покупатель с ID {} удален.", id);
    }

    /**
     * Обновляет данные покупателя.
     *
     * @param updatedCustomer объект покупателя с обновленными данными
     */
    public void updateCustomer(Customer updatedCustomer) {
        List<Customer> customers = loadCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == updatedCustomer.getId()) {
                customers.set(i, updatedCustomer);
                break;
            }
        }
        saveCustomers(customers);
        log.info("Данные покупателя с ID {} обновлены.", updatedCustomer.getId());
    }
}