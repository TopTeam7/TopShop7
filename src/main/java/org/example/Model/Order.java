package org.example.Model;

import org.example.OrderException.CustomerNotFoundException;
import org.example.OrderException.OrderNotFoundExcetion;
import org.example.Repository.CustomerRepository;
import org.example.Service.CustomerService;
import org.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class Order {
    private static final Logger log = LoggerFactory.getLogger(Order.class);
    private Integer orderId;
    private int customerId;
    private int productId;

    private String orderStatus;
    private Customer customer;
    private Product product;
    private CustomerService customerService;
    private ProductService productService;

    public Order(Integer orderId, int customerId,String orderStatus, int productId) {
        log.info("Создание заказа");
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.orderStatus = orderStatus;
    }

    public Order(String OrderFromFile) throws CustomerNotFoundException {
        try {
            log.info("Получение заказа из файла");
            String[] arrayFromFile = OrderFromFile.split(";");
            this.orderId = Integer.parseInt(arrayFromFile[0]);
//            this.customer = customerService.findCustomerById(Integer.parseInt(arrayFromFile[1]));
//            this.product = productService.getProduct(Integer.parseInt(arrayFromFile[2]));
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return customerId == order.customerId && productId == order.productId
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
