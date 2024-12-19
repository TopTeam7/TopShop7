package org.example.Model;

import java.util.Objects;

public class Order {
    private Integer orderId;
    private int buyerId;
    private int productId;
    private String orderStatus;

    public Order(Integer orderId, int buyerId,String orderStatus,int productId) {
        this.orderId = orderId;
        this.buyerId = buyerId;

        this.productId = productId;
        this.orderStatus = orderStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
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
        return buyerId == order.buyerId && productId == order.productId
                && Objects.equals(orderId, order.orderId)
                && Objects.equals(orderStatus, order.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, buyerId, productId, orderStatus);
    }

    @Override
    public String toString() {
        return "ID Заказа =" + orderId +
                ", Id Покупателя=" + buyerId +
                ", Id продукта =" + productId +
                ", Статус заказа ='" + orderStatus;
    }
}
