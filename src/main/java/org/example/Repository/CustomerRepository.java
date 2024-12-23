package org.example.Repository;

import org.example.Model.Customer;
import org.example.Model.CustomerType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с покупателями.
 */
public class CustomerRepository {
    private final String filePath = "src/main/resources/customers.txt";
    private final String idFilePath = "src/main/resources/last_id.txt";

    /**
     * Загружает всех покупателей из файла.
     *
     * @return список покупателей.
     */
    public List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(new Customer(line)); // Используем конструктор из строки
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return customers;
    }

    /**
     * Сохраняет список покупателей в файл.
     *
     * @param customers список покупателей.
     */
    public void saveCustomers(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customers) {
                writer.write(customer.toString()); // Используем toString() для сохранения
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /**
     * Генерирует новый уникальный ID.
     *
     * @return новый ID.
     */
    public int generateNewId() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(idFilePath))) {
            String line = reader.readLine();
            if (line != null) {
                lastId = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла last_id.txt: " + e.getMessage());
        }

        lastId++;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFilePath))) {
            writer.write(String.valueOf(lastId));
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл last_id.txt: " + e.getMessage());
        }

        return lastId;
    }
}