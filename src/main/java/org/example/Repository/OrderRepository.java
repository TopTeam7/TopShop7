
package org.example.Repository;

import org.example.Model.Order;
import org.example.OrderException.OrderNotFoundExcetion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class OrderRepository {
    private static final Logger log = LoggerFactory.getLogger(OrderRepository.class);
    String fileName = "orders.txt";
    String directory = "src/main/resources/";
    String fileNameId = "ordersId.txt";
    Path filePath = Path.of(directory + fileName);
    Path filePathID = Path.of(directory + fileNameId);

    private final List<Order> orders = new ArrayList<>();

    public OrderRepository() {
        try {
            if (!Files.exists(filePath)) {
                log.info("Создание файла заказов");
                Files.createFile(filePath);
            }
            if (!Files.exists(filePathID)) {
                Files.createFile(filePathID);
                int orderCountId = 0;
                Files.write(filePathID, (orderCountId + "\n").getBytes());
                log.info("Создание файла с Id номерами");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            log.warn("Не удалось создать файлы");
        }

    }

    /**
     * Метод принимает параметр
     *
     * @param order Метод добавляет новы заказ в лист заказов и присвавает Id номер
     * @return Order
     */
    public Order saveOrder(Order order) {
        try {
            log.info("Получение последненого Id номера");
            order.setOrderId(Integer.parseInt(Files.readAllLines(filePathID).getLast()) + 1);
        } catch (IOException e) {
            log.warn("Не удалось получить Id номер ");
            throw new RuntimeException(e);
        }
        try {
            log.info("Запись Id номера в файл");
            Files.write(filePathID, (order.getOrderId() + "\n").getBytes(), StandardOpenOption.APPEND);
            log.info("Запись заказа в файл");
            Files.write(filePath, order.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.warn("Запись не удалась");
            throw new RuntimeException(e);
        }
        return order;

    }

    /**
     * Метод не принимает параметров
     * Метод возврфщает лист c заказами
     *
     * @return List<String>
     */
    public List<String> listOrder() {
        try {
            log.info("получение листа заказов{}", Files.readAllLines(filePath));
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            log.warn("Не удалось получить лист заказов");
            throw new RuntimeException(e);
        }

    }

    /**
     * Метод принимает параметры
     *
     * @param orderId   типа int
     * @param newStatus типа String
     * Метод достает заказ из листа по Id номеру меняет его статус
     */
    public void changeStatusOrder(int orderId, String newStatus) {
        try {
            log.info("Изменение статуса заказа");
            Order order = orders.stream()
                    .filter(o -> o.getOrderId().equals(orderId))
                    .findFirst()
                    .orElse(null);
            if (!(order == null)) {
                order.setOrderStatus(newStatus);
                loadToFileFromOrdersList(orders);
            }
        } catch (IndexOutOfBoundsException | IOException e) {
            log.warn("Не удалось изменить статус заказа");
            throw new OrderNotFoundExcetion("Заказа с таким номером не существует");
        }
    }

    /**
     * Метод принимает параметр
     *
     * @param id типа int
     *Метод возвращает заказ по номеру Id
     * @return Order
     */
    public Order findOrderById(int id) {

        log.info("Выгрузка списка заказов в лист");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String fileOrders;
            orders.clear();
            log.info("Очистка листа перед выгрузкой заказов из файла");
            while ((fileOrders = reader.readLine()) != null) {
                orders.add(new Order(fileOrders));
            }
            log.info("Список заказов выгружен в лист");
            return orders.stream()
                    .filter(order -> order.getOrderId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new OrderNotFoundExcetion("Заказ с ID " + id + " не найден"));
        } catch (IOException | NullPointerException e) {
            log.warn("Не удалось выгрузить срисок заказов в лист");
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод очищает файл от старых записей и записывает в файл обнавленные заказы
     * Метод принимает лист заказов @ List<Order>
     *
     */
    public void loadToFileFromOrdersList(List<Order> orders) throws IOException {
        log.info("Загрузка изменений в файл");
        Files.write(filePath, "".getBytes());
        for (Order order : orders) {
            Files.write(filePath, order.toString().getBytes(), StandardOpenOption.APPEND);
        }
    }
}
