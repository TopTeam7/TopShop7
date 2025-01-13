package org.example.Model;

public enum OrderStatus {
    NEW("Новый"),
    PROCESS("В процессе"),
    CANCELED("Отменен"),
    COMPLETED("Выполнен");
    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
