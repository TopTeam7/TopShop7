package org.example.Controller;

import org.example.OrderException.CustomerNotFoundException;
import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.Service.CustomerService;

import java.util.List;
import java.util.Scanner;

/**
 * Контроллер для управления покупателями.
 */
public class CustomerController {
    private final CustomerService service;
    private final Scanner scanner;

    public CustomerController(CustomerService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Запускает консольное меню.
     */
    public void run() {
        while (true) {
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("3. Найти покупателя по ID");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> showAllCustomers();
                case 3 -> findCustomerById();
                case 4 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Введите имя покупателя: ");
        String name = scanner.nextLine();
        System.out.print("Введите тип покупателя (NEW, REGULAR, VIP): ");
        CustomerType type = CustomerType.valueOf(scanner.nextLine().toUpperCase());
        service.addCustomer(name, type);
        System.out.println("Покупатель добавлен!");
    }

    private void showAllCustomers() {
        List<Customer> customers = service.getAllCustomers();
        customers.forEach(System.out::println);
    }

    private void findCustomerById() {
        System.out.print("Введите ID покупателя: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        try {
            Customer customer = service.findCustomerById(id);
            System.out.println("Найден покупатель: " + customer);
        } catch (org.example.Service.CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}