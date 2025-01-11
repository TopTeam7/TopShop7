package org.example.Service;

import org.example.exception.CustomerNotFoundException;
import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.Repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Класс, содержащий бизнес-логику для работы с покупателями.
 */
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository repository;

    /**
     * Конструктор для создания CustomerService.
     *
     * @param repository репозиторий для работы с покупателями
     */
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Добавляет нового покупателя.
     *
     * @param name имя покупателя
     * @param type тип покупателя
     */
    public void addCustomer(String name, String type) {
        Customer customer = new Customer(0, name, CustomerType.valueOf(type)); // Используем CustomerType
        repository.addCustomer(customer); // Исправлено: передаем объект customer
    }

    /**
     * Возвращает список всех покупателей.
     *
     * @return список покупателей
     */
    public List<Customer> getAllCustomers() {
        return repository.loadCustomers();
    }

    /**
     * Находит покупателя по ID.
     *
     * @param id идентификатор покупателя
     * @return найденный покупатель
     * @throws CustomerNotFoundException если покупатель не найден
     */
    public Customer findCustomerById(int id) throws CustomerNotFoundException {
        return repository.loadCustomers().stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Покупатель с ID {} не найден", id);
                    return new CustomerNotFoundException("Покупатель с ID " + id + " не найден.");
                });
    }
}