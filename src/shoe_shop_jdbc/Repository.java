package shoe_shop_jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Timestamp;
import java.sql.Types;

public class Repository {

    private ArrayList<Shoe> shoes = new ArrayList();
    private ArrayList<Brand> brands = new ArrayList();
    private ArrayList<Category> categories = new ArrayList();
    private ArrayList<Order> orders = new ArrayList();
    private ArrayList<Customer> customers = new ArrayList();
    private ArrayList<Review> reviews = new ArrayList();
    private ArrayList<Grade> grades = new ArrayList();
    private ArrayList<OutOfStock> outOfStock = new ArrayList();

    private Properties p;
//    private String name;
//    private String password;
    private ResultSet rs;

    public Repository() {
//        this.name = name;
//        this.password = password; 
        p = new Properties();
        try {
            p.load(new FileInputStream("src//shoe_shop_jdbc//myP.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection(p.getProperty("ConnectionString"), p.getProperty("Name"), p.getProperty("Password"));) {

            categoryRepo(con);
            brandRepo(con);
            shoeRepo(con);
            customerRepo(con);
            orderRepo(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shoeRepo(Connection con) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select * from shoe;");
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int brandID = rs.getInt("brandID") - 1;
            String color = rs.getString("color");
            int size = rs.getInt("shoe_size");
            double price = rs.getDouble("price");
            int stock = rs.getInt("stock");

            Shoe tempShoe = new Shoe(id, name, brands.get(brandID), color, size, price, stock);
            shoes.add(tempShoe);
        }

        ps = con.prepareStatement("select * from belongs_to_category;");
        rs = ps.executeQuery();

        while (rs.next()) {
            int shoeID = rs.getInt("shoeID") - 1;
            int categotyID = rs.getInt("categoryID") - 1;

            shoes.get(shoeID).addToCategories(categories.get(categotyID));
        }
    }

    private void brandRepo(Connection con) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select * from brand;");
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Brand tempBrand = new Brand(id, name);
            brands.add(tempBrand);
        }
    }

    private void categoryRepo(Connection con) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select * from category;");
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Category tempCategory = new Category(id, name);
            categories.add(tempCategory);
        }
    }

    public void customerRepo(Connection con) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select * from customer;");
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String city = rs.getString("city");
            String email = rs.getString("email");

            Customer tempCustomer = new Customer(id, name, password, address, city, email);
            customers.add(tempCustomer);
        }
    }

    private void orderRepo(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "select placed_order.id as orderID, order_content.shoeID, placed_order.customerID, placed_order.orderdate, placed_order.expedited from order_content \n"
                + "inner join placed_order on order_content.placed_orderID = placed_order.id order by orderID;");

        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("orderID");
            int shoeID = rs.getInt("shoeID") - 1;
            int customerID = rs.getInt("customerID") - 1;
            boolean expedited = rs.getBoolean("expedited");
            Timestamp orderdate = rs.getTimestamp("orderdate");

            if (orders.stream().anyMatch(o -> o.getId() == id)) {
                orders.get(id - 1).addToShoes(shoes.get(shoeID));

            } else {
                Order tempOrder = new Order(id, orderdate, expedited);
                tempOrder.addToShoes(shoes.get(shoeID));
                orders.add(tempOrder);
                customers.get(customerID).addToOrders(orders.get(id - 1));
            }

        }
    }

    public void addToCart(int shoeID, int customerID, int orderID) {

        try (Connection con = DriverManager.getConnection(p.getProperty("ConnectionString"), p.getProperty("Name"), p.getProperty("Password"));) {
            PreparedStatement ps = con.prepareStatement("Call AddToCart(?, ?, ?);");
            if (customerID != -1) {
                ps.setInt(1, customerID);
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setInt(2, orderID);
            ps.setInt(3, shoeID);

            rs = ps.executeQuery();
            String message = "";
            while (rs.next()) {
                message = rs.getString("message");
            }

            if (message.equalsIgnoreCase("ERROR")) {
                System.out.println("something went wrong... ");
            } else if (message.equals("DONE")) {
                System.out.println("Everything went ok!");
            } else {
                System.out.println("dooh");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void expediteOrder(int orderID) {

        try (Connection con = DriverManager.getConnection(p.getProperty("ConnectionString"), p.getProperty("Name"), p.getProperty("Password"));) {
            PreparedStatement ps = con.prepareStatement("Update placed_order set expedited = true where ID = ?;");
            ps.setInt(1, orderID);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateRepo() {

        shoes = new ArrayList();
        brands = new ArrayList();
        categories = new ArrayList();
        orders = new ArrayList();
        customers = new ArrayList();
        reviews = new ArrayList();
        grades = new ArrayList();
        outOfStock = new ArrayList();

        try (Connection con = DriverManager.getConnection(p.getProperty("ConnectionString"), p.getProperty("Name"), p.getProperty("Password"));) {

            categoryRepo(con);
            brandRepo(con);
            shoeRepo(con);
            customerRepo(con);
            orderRepo(con);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Shoe> getShoes() {
        return shoes;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

}
