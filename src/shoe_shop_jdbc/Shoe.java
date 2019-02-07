package shoe_shop_jdbc;

import java.util.ArrayList;

public class Shoe {

    private int id;
    private String name;
    private Brand brand;
    private String color;
    private int size;
    private double price;
    private int stock;

    private ArrayList<Category> categories;

    public Shoe(int id, String name, Brand brand, String color, int size, double price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.price = price;
        this.stock = stock;
        categories = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addToCategories(Category category) {
        categories.add(category);
    }
}
