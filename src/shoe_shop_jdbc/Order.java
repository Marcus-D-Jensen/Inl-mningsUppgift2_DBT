package shoe_shop_jdbc;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {

    private int id;
    private Timestamp orderdate;
    private double totPrice;
    private ArrayList<Shoe> shoes;
    private boolean expedited;

    public Order(int id, Timestamp orderdate, boolean expedited) {
        this.id = id;
        this.orderdate = orderdate;
        this.expedited = expedited;
        shoes = new ArrayList();

    }

    public int getId() {
        return id;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public double getTotPrice() {
        totPrice = 0;
        for (Shoe shoe : shoes) {
            totPrice += shoe.getPrice();
        }
        return totPrice;
    }

    public void addToShoes(Shoe shoe) {
        shoes.add(shoe);
    }

    public ArrayList<Shoe> getShoes() {
        return shoes;
    }

    public boolean isExpedited() {
        return expedited;
    }

    public void setExpedited(boolean expedited) {
        this.expedited = expedited;
    }

}
