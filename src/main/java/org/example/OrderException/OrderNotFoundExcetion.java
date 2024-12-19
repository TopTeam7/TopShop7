package org.example.OrderException;

public class OrderNotFoundExcetion extends RuntimeException{


    public OrderNotFoundExcetion(String message) {
        super(message);
    }
}
