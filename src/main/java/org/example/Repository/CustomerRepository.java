package org.example.Repository;

import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.OrderException.CustomerNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с данными покупателей (сохранение, загрузка, поиск).
 */
public class CustomerRepository {
    private static final String CUSTOMERS_FILE_PATH = "src/main/resources/customers.txt"; // Путь к файлу с данными покупателей
    private static final String LAST_ID_FILE_PATH = "src/main/resources/last_id.txt"; // Путь к файлу с последним использованным ID
    private int lastId; // Последний использованный ID

    /**
     * Конструктор. Загружает последний использованный ID из файла.
     */
    public CustomerRepository() {
        this.lastId = loadLastId();
    }

    /**
     * Загружает последний использованный ID из файла.
     *
     * @return последний использованный ID
     */
    private int loadLastId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LAST_ID_FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла last_id.txt: " + e.getMessage());
        }
        return 0; // Если файл пуст или не существует, начинаем с 0
    }

    /**
     * Сохраняет последний использованный ID в файл.
     */
    private void saveLastId() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE_PATH))) {
            writer.write(String.valueOf(lastId));
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл last_id.txt: " + e.getMessage());
        }
    }

    /**
     * Сохраняет всех покупателей в файл.
     *
     * @param customers список покупателей
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void saveAll(List<Customer> customers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMERS_FILE_PATH))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Загружает всех покупателей из файла.
     *
     * @return список покупателей
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public List<Customer> findAll() throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(new Customer(line)); // Создаём объект из строки
            }
        }
        return customers;
    }

    /**
     * Добавляет нового покупателя.
     *
     * @param customer объект покупателя
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void addCustomer(Customer customer) throws IOException {
        List<Customer> customers = findAll();
        customer = new Customer(++lastId, customer.getName(), customer.getType()); // Генерируем новый ID
        customers.add(customer);
        saveAll(customers);
        saveLastId(); // Сохраняем новый последний ID
    }

    /**
     * Находит покупателя по ID.
     *
     * @param id идентификатор покупателя
     * @return объект покупателя
     * @throws IOException если произошла ошибка ввода-вывода
     * @throws CustomerNotFoundException если покупатель не найден
     */
    public Customer findById(int id) throws IOException, CustomerNotFoundException {
        List<Customer> customers = findAll();
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Покупатель с ID " + id + " не найден.");
    }
}