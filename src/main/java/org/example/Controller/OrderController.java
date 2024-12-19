package org.example.Controller;


import org.example.OrderException.OrderNotFoundExcetion;
import org.example.Service.OrderService;

import java.util.Scanner;

public class OrderController {
    Scanner sc = new Scanner(System.in);

    private final OrderService orderService;
    private boolean cycleOrderProgram = true;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void startOrder(boolean cycleProgram) {
        cycleOrderProgram = cycleProgram;
        while (cycleOrderProgram) {
            System.out.println("Что бы добавить заказ нажмите цифру 1");
            System.out.println("Что бы увидеть все заказы нажмите цифру 2");
            System.out.println("Что бы найти заказ по ID нажмите цифру 3");
            System.out.println("Что бы изменить статус заказа нажмите цифру 4");
            System.out.println("Что бы вернуться нажмите цифру 0");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1 -> addOrder();
                    case 2 -> showOrders();
                    case 3 -> getOrderById();
                    case 4 -> changeStatusOrder();
                    default -> back();

                }
            } catch (OrderNotFoundExcetion e) {
                System.out.println(e);
            }
        }
    }

    public void addOrder() {
        System.out.println("Enter the buyer ID");
        int buyerId = sc.nextInt();

        System.out.println("Enter product ID");
        int productId = sc.nextInt();

        System.out.println("Enter status of the order '\n1-new ,'\n2- process, '\n3- completed, '\n4- canceled");
        int orderStatusValue = sc.nextInt();

        String orderStatus;

        switch (orderStatusValue) {
            case 1 -> orderStatus = "new";
            case 2 -> orderStatus = "process";
            case 3 -> orderStatus = "completed";
            default -> orderStatus = "canceled";
        }

        String orderView = orderService.addOrder(buyerId, productId, orderStatus).toString();
        System.out.println(orderView);
    }

    public void showOrders() {
        String view = orderService.listToView().toString();
        System.out.println(view);
    }

    public void changeStatusOrder() {
        orderService.changeStatusOrder();
    }

    public void getOrderById() {
        System.out.println("Введите Id номер заказа");
        int id = sc.nextInt();
        System.out.println(orderService.getOrderById(id));
    }

    public void back() {
        cycleOrderProgram = false;
    }


}
