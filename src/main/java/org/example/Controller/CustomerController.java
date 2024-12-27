package org.example.Controller;

import org.example.Service.CustomerService;
import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.OrderException.CustomerNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Контроллер для управления покупателями.
 */
public class CustomerController {
    private final CustomerService customerService;
    private final Scanner scanner;

    /**
     * Конструктор.
     *
     * @param customerService сервис для работы с покупателями
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Отображает меню управления покупателями.
     *
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void showMenu() throws IOException {
        while (true) {
            System.out.println("===== Управление покупателями =====");
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("3. Найти покупателя по ID");
            System.out.println("0. Назад");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> showAllCustomers();
                case 3 -> findCustomerById();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    /**
     * Добавляет нового покупателя.
     *
     * @throws IOException если произошла ошибка ввода-вывода
     */
    private void addCustomer() throws IOException {
        System.out.print("Введите имя покупателя: ");
        String name = scanner.nextLine();
        System.out.print("Введите тип покупателя (NEW, REGULAR, VIP): ");
        String typeInput = scanner.nextLine().toUpperCase();

        try {
            CustomerType type = CustomerType.valueOf(typeInput); // Преобразуем строку в enum
            customerService.addCustomer(name, type);
            System.out.println("Покупатель добавлен!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверный тип покупателя. Допустимые значения: NEW, REGULAR, VIP.");
        }
    }

    /**
     * Отображает список всех покупателей.
     *
     * @throws IOException если произошла ошибка ввода-вывода
     */
    private void showAllCustomers() throws IOException {
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * Находит покупателя по ID и выводит его данные.
     */
    private void findCustomerById() {
        System.out.print("Введите ID покупателя: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        try {
            Customer customer = customerService.getCustomerById(id);
            System.out.println("Найден покупатель: " + customer);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}