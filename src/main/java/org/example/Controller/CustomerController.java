package org.example.Controller;

import org.example.Model.Customer;
import org.example.Service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

/**
 * Контроллер для управления покупателями.
 */
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService; // Убрана инициализация
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор для создания CustomerController.
     *
     * @param customerService сервис для работы с покупателями
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Добавляет нового покупателя.
     *
     * @param name имя покупателя
     * @param type тип покупателя
     */
    public void addCustomer(String name, String type) {
        customerService.addCustomer(name, type);
        log.info("Добавлен новый покупатель: {}, {}", name, type);
    }

    /**
     * Показывает список всех покупателей.
     */
    public void showAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(System.out::println);
    }

    /**
     * Находит покупателя по ID.
     *
     * @param id идентификатор покупателя
     */
    public void findCustomerById(int id) {
        try {
            Customer customer = customerService.findCustomerById(id);
            System.out.println("Найден покупатель: " + customer);
        } catch (RuntimeException e) {
            log.error("Ошибка при поиске покупателя: ", e);
        }
    }

    public void startCustomer() {
    }
}