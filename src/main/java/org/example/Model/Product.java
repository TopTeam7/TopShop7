package org.example.Model;



public class Product {
    private Integer id;
    private String title;
    private int price;
    private String category;

    public Product(Integer id, String title, int price, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public Product(String fileProducts) {
        String[] parts = fileProducts.split(";");
        this.id = Integer.parseInt(parts[0]);
        this.title = parts[1];
        this.price = Integer.parseInt(parts[2]);
        this.category = parts[3];
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return id + ";" + title + ";" + price + ";" + category;
    }
}

