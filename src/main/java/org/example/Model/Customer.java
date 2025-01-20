package org.example.Model;


/**
 * Класс, представляющий покупателя.
 * Содержит информацию о покупателе: идентификатор, имя и тип.

 */
public class Customer {
    private final int id;
    private final String name;
    private final CustomerType type;

    /** Конструктор для создания объекта покупателя.
     *
             * @param id   уникальный идентификатор покупателя
     * @param name имя покупателя
     * @param type тип покупателя (NEW, REGULAR, VIP)
     */
    public Customer(int id, String name, CustomerType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }


    /**
     * Возвращает идентификатор покупателя.
     *
     * @return идентификатор покупателя
     */
    public int getId() {
        return id;
    }

    /** Возвращает имя покупателя.
     *
             * @return имя покупателя
     */
    public String getName() {
        return name;
    }

    /** Возвращает тип покупателя.
     *
     * @return тип покупателя (NEW, REGULAR, VIP)
     */
    public CustomerType getType() {
        return type;
    }


    /**
     * Возвращает строковое представление объекта покупателя.
     * Формат: "id;name;type".
     *
     * @return строковое представление покупателя

     */
    @Override
    public String toString() {
        return id + ";" + name + ";" + type;
    }
}