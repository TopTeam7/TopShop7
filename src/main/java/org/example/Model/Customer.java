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
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}