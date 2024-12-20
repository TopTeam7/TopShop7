package org.example.OrderException;

/**
 * Исключение, выбрасываемое при отсутствии покупателя.
 */
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}