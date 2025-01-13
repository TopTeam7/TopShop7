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
    private final CustomerService customerService;
    private final Scanner scanner = new Scanner(System.in);
    private boolean cycleCustomerProgram = true;

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

    /**
     * Запускает меню управления покупателями.
     */
    public void startCustomer(boolean cycleProgram) {
        cycleCustomerProgram = cycleProgram;
        while (cycleCustomerProgram) {
            System.out.println("\nМеню управления покупателями:");
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("3. Найти покупателя по ID");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCustomerMenu();
                case 2 -> showAllCustomers();
                case 3 -> findCustomerByIdMenu();
                case 0 ->  back();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    /**
     * Меню для добавления покупателя.
     */
    private void addCustomerMenu() {
        System.out.print("Введите имя покупателя: ");
        String name = scanner.nextLine();
        System.out.print("Введите тип покупателя (NEW, REGULAR, VIP): ");
        String type = scanner.nextLine();
        addCustomer(name, type);
    }

    /**
     * Меню для поиска покупателя по ID.
     */
    private void findCustomerByIdMenu() {
        System.out.print("Введите ID покупателя: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        findCustomerById(id);
    }
    public void back() {
        cycleCustomerProgram = false;
    }


}
