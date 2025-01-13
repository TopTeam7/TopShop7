
package org.example.Controller;

import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.Service.CustomerService;
import org.example.exception.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

/* Контроллер для управления покупателями.
        */
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final Scanner scanner = new Scanner(System.in);
    private boolean cycleCustomerProgram = true;

    /* Конструктор для создания CustomerController.
            *
            * @param customerService сервис для работы с покупателями
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /* Запускает меню управления покупателями.
            *
            * @param cycleProgram флаг для управления циклом программы
     */
    public void startCustomer(boolean cycleProgram) {
        cycleCustomerProgram = cycleProgram;
        while (cycleCustomerProgram) {
            System.out.println("\"===== Управление покупателями =====\"");
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("3. Найти покупателя по ID");
            System.out.println("4. Удалить покупателя по ID");
            System.out.println("5. Редактировать покупателя");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCustomerMenu();
                case 2 -> showAllCustomers();
                case 3 -> findCustomerByIdMenu();
                case 4 -> deleteCustomerMenu();
                case 5 -> updateCustomerMenu();
                case 0 -> back();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    /* Меню для добавления покупателя.
            */
    private void addCustomerMenu() {
        System.out.print("Введите имя покупателя: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Ошибка: Имя покупателя не может быть пустым.");
            return;
        }

        System.out.print("Введите тип покупателя (NEW, REGULAR, VIP): ");
        String type = scanner.nextLine().trim().toUpperCase();
        try {
            CustomerType customerType = CustomerType.valueOf(type);
            customerService.addCustomer(name, type);
            System.out.println("Покупатель добавлен.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверный тип покупателя. Допустимые значения: NEW, REGULAR, VIP.");
        }
    }

    /* Показывает список всех покупателей.
            */
    private void showAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(System.out::println);
    }

    /* Меню для поиска покупателя по ID.
            */
    private void findCustomerByIdMenu() {
        System.out.print("Введите ID покупателя: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Customer customer = customerService.findCustomerById(id);
            System.out.println("Найден покупатель: " + customer);
        } catch (CustomerNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }


            /* Меню для удаления покупателя по ID.
            */
    private void deleteCustomerMenu() {
        System.out.print("Введите ID покупателя для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            customerService.deleteCustomer(id);
            System.out.println("Покупатель с ID " + id + " удален.");
        } catch (CustomerNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /* Меню для редактирования покупателя.
            */
    private void updateCustomerMenu() {
        System.out.print("Введите ID покупателя для редактирования: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Customer customer = customerService.findCustomerById(id);
            System.out.println("Текущие данные покупателя: " + customer);

            System.out.print("Введите новое имя покупателя: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Ошибка: Имя покупателя не может быть пустым.");
                return;
            }

            System.out.print("Введите новый тип покупателя (NEW, REGULAR, VIP): ");
            String type = scanner.nextLine().trim().toUpperCase();
            try {
                CustomerType customerType = CustomerType.valueOf(type);
                customerService.updateCustomer(id, name, type);
                System.out.println("Данные покупателя обновлены.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: Неверный тип покупателя. Допустимые значения: NEW, REGULAR, VIP.");
            }
        } catch (CustomerNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Завершает цикл меню и возвращает в главное меню.
     */
    public void back() {
        cycleCustomerProgram = false;
    }
}