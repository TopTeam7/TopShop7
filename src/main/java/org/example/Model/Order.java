
package org.example.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public class Order {
    private static final Logger log = LoggerFactory.getLogger(Order.class);
    private Integer orderId;
    private int customerId;
    private String productId;
    private String orderStatus;

    public Order(Integer orderId, int customerId, String orderStatus, String productId) {
        log.info("Создание заказа");
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.productId = productId;

    }

    public Order(String OrderFromFile) {
        try {
            log.info("Получение заказа из файла");
            String[] arrayFromFile = OrderFromFile.split(";");
            this.orderId = Integer.parseInt(arrayFromFile[0]);
            this.customerId = Integer.parseInt(arrayFromFile[1]);
            this.productId = arrayFromFile[2];
            this.orderStatus = arrayFromFile[3];
        } catch (NullPointerException e) {
            log.error("Заказ из файла не получен");
            System.out.println(e.getMessage());
        }
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;

    }


    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return customerId == order.customerId && productId.equals(order.productId)
                && Objects.equals(orderId, order.orderId)
                && Objects.equals(orderStatus, order.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, productId, orderStatus);
    }

    @Override
    public String toString() {
        return orderId +
                ";" + customerId +
                ";" + productId +
                ";" + orderStatus + "\n";
    }


}
