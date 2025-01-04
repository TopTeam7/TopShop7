package org.example.Model;

public class Product {
    private Integer id; // ID продукта
    private String title; // Название продукта
    private int price; // Цена продукта
    private String category; // Категория продукта

    // Конструктор для создания нового продукта с заданными параметрами
    public Product(Integer id, String title, int price, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    // Конструктор для создания продукта из строки, разделенной точками с запятой
    public Product(String fileProducts) {
        String[] parts = fileProducts.split(";"); // Разделение строки на части
        this.id = Integer.parseInt(parts[0]); // Преобразование первой части в ID
        this.title = parts[1]; // Вторая часть - название продукта
        this.price = Integer.parseInt(parts[2]); // Третья часть - цена продукта
        this.category = parts[3]; // Четвертая часть - категория продукта
    }

    // Конструктор по умолчанию
    public Product() {
    }

    // Геттер для ID продукта
    public Integer getId() {
        return id;
    }

    // Сеттер для ID продукта
    public void setId(Integer id) {
        this.id = id;
    }

    // Геттер для названия продукта
    public String getTitle() {
        return title;
    }

    // Сеттер для названия продукта
    public void setTitle(String title) {
        this.title = title;
    }

    // Геттер для цены продукта
    public int getPrice() {
        return price;
    }

    // Сеттер для цены продукта
    public void setPrice(int price) {
        this.price = price;
    }

    // Геттер для категории продукта
    public String getCategory() {
        return category;
    }

    // Сеттер для категории продукта
    public void setCategory(String category) {
        this.category = category;
    }

    // Переопределение метода toString для представления продукта в виде строки
    @Override
    public String toString() {
        return id + ";" + title + ";" + price + ";" + category; // Возвращает строку, содержащую ID, название, цену и категорию продукта, разделенные точками с запятой
    }
}
