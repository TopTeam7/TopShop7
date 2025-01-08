package org.example.Controller;


import org.example.Model.OrderStatus;
import org.example.OrderException.OrderNotFoundExcetion;
import org.example.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    Scanner sc = new Scanner(System.in);
    Scanner scanner = new Scanner(System.in);

    private final OrderService orderService;
    private boolean cycleOrderProgram = true;
    private String orderStatus;
    private OrderStatus newStatus;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void startOrder(boolean cycleProgram) {
        cycleOrderProgram = cycleProgram;
        while (cycleOrderProgram) {
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
                    case 3 -> getOrderById();
                    case 4 -> changeStatusOrder();
                    case 0 -> back();
                    default -> System.out.println("Введите корректное число");

                }
            } catch (OrderNotFoundExcetion e) {
                log.warn("Заказ с таким номером не найден");
                System.out.println(e);
            }
        }
    }

    public void addOrder() {
        log.info("Добавление заказа");
        System.out.println("Введите ID покупателя");
        int buyerId = sc.nextInt();

        System.out.println("Введите ID продукта");
        int productId = sc.nextInt();

        System.out.println("Введите статус заказа '\n1-new,'\n2- process, '\n3- completed, '\n4- canceled");
        int orderStatusValue = sc.nextInt();

        log.info("Выбор стстуса заказа");
        switch (orderStatusValue) {

            case 1 -> orderStatus = String.valueOf(OrderStatus.NEW);
            case 2 -> orderStatus = String.valueOf(OrderStatus.PROCESS);
            case 3 -> orderStatus = String.valueOf(OrderStatus.COMPLETED);
            case 4 -> orderStatus = String.valueOf(OrderStatus.CANCELED);
            default -> System.out.println("Введите корректный номер");
        }

        String orderView = orderService.addOrder(buyerId, productId, orderStatus).toString();
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
            String view = orderService.listToView().toString();
            System.out.println(view);
            log.info("Список заказов {}", view);
        }
    }

    /**
     * Метод не принимает параметры
     * Метод позволяет ввести  новый статус заказа
     */
    public void changeStatusOrder() {
        log.info("Выбран пункт изменения статуса заказов");
        System.out.println("Введите новый статус заказа \n 1: New\n 2: Process\n 3: Completed\n 4: Canceled");
        int orderStatusValue = sc.nextInt();

        switch (orderStatusValue) {
            case 1 -> newStatus = OrderStatus.NEW;
            case 2 -> newStatus = OrderStatus.PROCESS;
            case 3 -> newStatus = OrderStatus.COMPLETED;
            case 4 -> newStatus = OrderStatus.CANCELED;
            default -> System.out.println("Введите корректный номер");
        }
        orderService.changeStatusOrder(getOrderById(), String.valueOf(newStatus));
        log.info("Статус заказа изменен");
    }

    /**
     * Метод выводить в консоль заказ по указанному Id
     * Метод не принимает.
     * Метод возвращает объект типа Order
     *
     * @return Order
     */
    public int getOrderById() {
        log.info("Получение заказа по Id");
        System.out.println("Введите Id номер заказа");
        int id = sc.nextInt();
        System.out.println(orderService.getOrderById(id));
        return orderService.getOrderById(id).getOrderId();

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
