package org.example.Controller;


import org.example.Model.OrderStatus;
import org.example.OrderException.OrderNotFoundExcetion;
import org.example.Service.CustomerService;
import org.example.Service.OrderService;
import org.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    Scanner sc = new Scanner(System.in);

    private final OrderService orderService;
    private boolean cycleOrderProgram;
    private String orderStatus;
    private OrderStatus newStatus;
    private final CustomerService customerService;
    private final ProductService productService;
    private boolean cycle;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    public void startOrder(boolean cycleProgram) {
        cycleOrderProgram = cycleProgram;
        while (cycleOrderProgram) {
            System.out.println("\"===== Управление заказами =====\"");
            System.out.println("1: Добавить заказ");
            System.out.println("2: Посмотреть все заказы");
            System.out.println("3: Найти заказ по ID");
            System.out.println("4: Изменить статус заказа");
            System.out.println("0: Назад");
            int choice = sc.nextInt();
            try {
                log.info("Начало работы с заказами");
                switch (choice) {
                    case 1 -> addOrder();
                    case 2 -> showOrders();
                    case 3 -> findOrderById();
                    case 4 -> changeStatusOrder();
                    case 0 -> back();
                    default -> System.out.println("Введите корректное число");
                }
            } catch (OrderNotFoundExcetion e) {
                sc.close();
                log.warn("Заказ с таким номером не найден");
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Метод не ринимает параметры.
     * Метод создает новый заказ.
     */
    public void addOrder() throws org.example.exception.CustomerNotFoundException {

        log.info("Добавление заказа");

        int customId = findCustomerById();
        setOrderStatus();
        String strProdId = addProductToOrder().toString();
        String orderView = orderService.addOrder(customId, orderStatus, strProdId).toString();
        log.info("Добавлен заказ {}", orderView);

    }

    /**
     * Метод не принимает параметры
     * метод выводит список заказов к консоль
     */
    public void showOrders() {
        log.info("Выбран список заказов");
        if (orderService.listToView().isEmpty()) {
            log.info("Список заказов пуст");
        } else {
            log.info("Список заказов выгружен из файла");
            orderService.listToView().forEach(System.out::println);
        }
    }

    /**
     * Метод не принимает параметры
     * Метод позволяет ввести  новый статус заказа
     */
    public void changeStatusOrder() {
        log.info("Выбран пункт изменения статуса заказов");
        System.out.println("Введите Id номер заказа");
        int id = sc.nextInt();
        System.out.println(orderService.findOrderById(id));

        System.out.println("Введите новый статус заказа \n 1: New\n 2: Process\n 3: Completed\n 4: Canceled");
        int orderStatusValue = sc.nextInt();

        switch (orderStatusValue) {
            case 1 -> newStatus = OrderStatus.NEW;
            case 2 -> newStatus = OrderStatus.PROCESS;
            case 3 -> newStatus = OrderStatus.COMPLETED;
            case 4 -> newStatus = OrderStatus.CANCELED;
            default -> System.out.println("Введите корректный номер");
        }
        log.info("Статус заказа изменен");
        orderService.changeStatusOrder(id, String.valueOf(newStatus));
    }

    /**
     * Метод ищет покупателя по Id
     * Метод возвращает Id номер покупателя
     *
     * @return int
     */
    public int findCustomerById() {
        System.out.println("Введите ID покупателя");
        int customerId = sc.nextInt();
        String[] customId = customerService.findCustomerById(customerId).toString().split(";");
        return Integer.parseInt(customId[0]);
    }

    /**
     * Метод не принимает параметры.
     * Метод в консоль заказ по ID
     */
    public void findOrderById() {
        log.info("Получение заказа по Id");
        System.out.println("Введите Id номер заказа");
        int id = sc.nextInt();
        log.info("Получен заказ {}", orderService.findOrderById(id));
        System.out.println(orderService.findOrderById(id));
    }

    /**Метод находит продукт по ID.
     *Метод возвращает Id  продукта
     * @return int
     */
    public int findProductId() {
        System.out.println("Введите ID продукта");
        int productId = sc.nextInt();
        String[] stringProductId = productService.getProduct(productId).toString().split(";");
        return Integer.parseInt(stringProductId[0]);
    }

    /**
     * Метод выбыра стстуса заказа
     * Метод ничего не возвращает
      */
    public void setOrderStatus() {
        System.out.println("Введите статус заказа \n 1: New\n 2: Process\n 3: Completed\n 4: Canceled");
        int orderStatusValue = sc.nextInt();

        log.info("Выбор стстуса заказа");

        switch (orderStatusValue) {

            case 1 -> orderStatus = String.valueOf(OrderStatus.NEW);
            case 2 -> orderStatus = String.valueOf(OrderStatus.PROCESS);
            case 3 -> orderStatus = String.valueOf(OrderStatus.COMPLETED);
            case 4 -> orderStatus = String.valueOf(OrderStatus.CANCELED);
            default -> System.out.println("Введите корректный номер");
        }
    }

    /**
     * Метод добавления ID продукта в заказ
     * @return StringBuilder.
     */
    public StringBuilder addProductToOrder() {
        int i = 0;
        int[] prodId = new int[10];
        do {
            prodId[i] = findProductId();
            System.out.println("Добавить еще продукт в заказ?");
            System.out.println("1: Да\n");
            System.out.println("0: Нет\n");
            int choice = sc.nextInt();
            if (choice == 1) {
                cycle = true;
                i++;
            } else {
                cycle = false;
            }
        } while (cycle);
        {
        }
        StringBuilder strProdId = new StringBuilder();
        for (int i1 : prodId) {
            if (i1 > 0) {
                if (!strProdId.isEmpty()) {
                    strProdId = new StringBuilder(strProdId + "," + i1);
                } else {
                    strProdId = new StringBuilder(strProdId + String.valueOf(i1));
                }
            } else {
                break;
            }
        }
        return strProdId;
    }

    /**
     * Метод не принимает
     * Метод возвращет в главное меню
     */
    public void back() {
        log.info("Выбрано возвращение в предыдущее меню");
        cycleOrderProgram = false;
    }
}
