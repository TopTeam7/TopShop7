package org.example.Service;

import org.example.OrderException.CustomerNotFoundException;
import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.Repository.CustomerRepository;

import java.util.List;

/**
 * Сервис для работы с покупателями.
 */
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Добавляет нового покупателя.
     *
     * @param name имя покупателя.
     * @param type тип покупателя.
     */
    public void addCustomer(String name, CustomerType type) {
        int newId = repository.generateNewId(); // Генерируем новый ID
        List<Customer> customers = repository.loadCustomers();
        customers.add(new Customer(newId, name, type));
        repository.saveCustomers(customers);
    }

    /**
     * Возвращает список всех покупателей.
     *
     * @return список покупателей.
     */
    public List<Customer> getAllCustomers() {
        return repository.loadCustomers();
    }

    /**
     * Находит покупателя по ID.
     *
     * @param id ID покупателя.
     * @return покупатель.
     * @throws CustomerNotFoundException если покупатель не найден.
     */
    public Customer findCustomerById(int id) throws CustomerNotFoundException {
        return repository.loadCustomers().stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Покупатель с ID " + id + " не найден"));
    }
}