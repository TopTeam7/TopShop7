package org.example.exception;

/** Исключение, выбрасываемое при попытке найти несуществующего покупателя.
        */
public class CustomerNotFoundException extends RuntimeException {
    /** Конструктор для создания исключения с сообщением.
            *
            * @param message сообщение об ошибке
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}