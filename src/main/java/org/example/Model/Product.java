package org.example.Model;

import java.util.Objects;



    public class Product {
        private Integer id;
        private String title;
        private double price;
        private String category;

        public Product(Integer id, String title, double price, String category) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.category = category;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            org.example.Model.Product products = (org.example.Model.Product) o;
            return Objects.equals(id, products.id) && Objects.equals(title, products.title) && Objects.equals(price, products.price) && Objects.equals(category, products.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, price, category);
        }

        @Override
        public String toString() {
            return "Product{" + id + " " + title + " " + price + " " + category + "}";
        }
}
