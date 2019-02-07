package shoe_shop_jdbc;

import java.util.ArrayList;

class Customer {

    private int id;
    private String name;
    private String password;
    private String address;
    private String city;
    private String email;
    private ArrayList<Order> orders;

    public Customer(int id, String name, String password, String address, String city, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.city = city;
        this.email = email;
        orders = new ArrayList();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addToOrders(Order order) {
        orders.add(order);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
