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
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                CustomerType type = CustomerType.valueOf(parts[2]);
                customers.add(new Customer(id, name, type));
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
                writer.write(customer.getId() + ";" + customer.getName() + ";" + customer.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}