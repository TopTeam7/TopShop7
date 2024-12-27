package org.example.OrderException;

/**
 * Исключение, выбрасываемое при попытке найти покупателя, который не существует.
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}