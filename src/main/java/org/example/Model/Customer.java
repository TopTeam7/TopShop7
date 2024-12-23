package org.example.Model;

/**
 * Сущность "Покупатель".
 */
public class Customer {
    private int id;
    private String name;
    private CustomerType type;

    public Customer(int id, String name, CustomerType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Конструктор из строки
    public Customer(String line) {
        String[] parts = line.split(";");
        this.id = Integer.parseInt(parts[0]);
        this.name = parts[1];
        this.type = CustomerType.valueOf(parts[2]);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + type;
    }
}