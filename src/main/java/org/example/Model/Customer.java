package org.example.Model;

/**
 * Класс, представляющий покупателя.
 */
public class Customer {
    private int id; // Уникальный идентификатор покупателя
    private String name; // Имя покупателя
    private CustomerType type; // Тип покупателя

    /**
     * Основной конструктор для создания объекта покупателя.
     *
     * @param id   уникальный идентификатор покупателя
     * @param name имя покупателя
     * @param type тип покупателя
     */
    public Customer(int id, String name, CustomerType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Конструктор для создания объекта покупателя из строки.
     *
     * @param data строка, содержащая данные о покупателе в формате "id;name;type"
     */
    public Customer(String data) {
        String[] parts = data.split(";");
        this.id = Integer.parseInt(parts[0]);
        this.name = parts[1];
        this.type = CustomerType.valueOf(parts[2]);
    }

    /**
     * Возвращает идентификатор покупателя.
     *
     * @return идентификатор покупателя
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя покупателя.
     *
     * @return имя покупателя
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает тип покупателя.
     *
     * @return тип покупателя
     */
    public CustomerType getType() {
        return type;
    }

    /**
     * Преобразует объект покупателя в строку для сохранения в файл.
     *
     * @return строка в формате "id;name;type"
     */
    @Override
    public String toString() {
        return id + ";" + name + ";" + type;
    }
}