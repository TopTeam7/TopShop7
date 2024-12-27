package org.example.Service;

import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.Repository.CustomerRepository;
import org.example.OrderException.CustomerNotFoundException;

import java.io.IOException;
import java.util.List;

/**
 * Сервис для работы с покупателями.
 */
public class CustomerService {
    private final CustomerRepository repository;

    /**
     * Конструктор.
     *
     * @param repository репозиторий для работы с данными покупателей
     */
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Добавляет нового покупателя.
     *
     * @param name имя покупателя
     * @param type тип покупателя
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void addCustomer(String name, CustomerType type) throws IOException {
        Customer customer = new Customer(0, name, type); // ID будет сгенерирован в репозитории
        repository.addCustomer(customer);
    }

    /**
     * Возвращает список всех покупателей.
     *
     * @return список покупателей
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public List<Customer> getAllCustomers() throws IOException {
        return repository.findAll();
    }

    /**
     * Находит покупателя по ID.
     *
     * @param id идентификатор покупателя
     * @return объект покупателя
     * @throws IOException если произошла ошибка ввода-вывода
     * @throws CustomerNotFoundException если покупатель не найден
     */
    public Customer getCustomerById(int id) throws IOException, CustomerNotFoundException {
        return repository.findById(id);
    }
}