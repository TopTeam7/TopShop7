package org.example.Model;

/**
 * Класс, представляющий продукт.
 */
public class Product {
    private Integer id;
    private String title;
    private int price;
    private String category;

    /**
     * Конструктор для создания продукта.
     *
     * @param id       уникальный идентификатор продукта
     * @param title    название продукта
     * @param price    цена продукта
     * @param category категория продукта
     */
    public Product(Integer id, String title, int price, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    /**
     * Конструктор для создания продукта из строки.
     *
     * @param fileProducts строка, содержащая информацию о продукте в формате "id;title;price;category"
     */
    public Product(String fileProducts) {
        String[] parts = fileProducts.split(";");
        this.id = Integer.parseInt(parts[0]);
        this.title = parts[1];
        this.price = Integer.parseInt(parts[2]);
        this.category = parts[3];
    }

    /**
     * Конструктор по умолчанию.
     */
    public Product() {
    }

    /**
     * Возвращает уникальный идентификатор продукта.
     *
     * @return уникальный идентификатор продукта
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор продукта.
     *
     * @param id уникальный идентификатор продукта
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Возвращает название продукта.
     *
     * @return название продукта
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название продукта.
     *
     * @param title название продукта
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает цену продукта.
     *
     * @return цена продукта
     */
    public int getPrice() {
        return price;
    }

    /**
     * Устанавливает цену продукта.
     *
     * @param price цена продукта
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Возвращает категорию продукта.
     *
     * @return категория продукта
     */
    public String getCategory() {
        return category;
    }

    /**
     * Устанавливает категорию продукта.
     *
     * @param category категория продукта
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Возвращает строковое представление продукта в формате "id;title;price;category".
     *
     * @return строковое представление продукта
     */
    @Override
    public String toString() {
        return id + ";" + title + ";" + price + ";" + category;
    }
}
