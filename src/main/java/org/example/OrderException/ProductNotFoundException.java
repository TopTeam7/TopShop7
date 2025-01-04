package org.example.OrderException;

public class ProductNotFoundException extends RuntimeException {
    // Конструктор для создания нового исключения с заданным сообщением
    public ProductNotFoundException(String message) {
        super(message); // Вызов конструктора суперкласса RuntimeException с передачей сообщения
    }
}
