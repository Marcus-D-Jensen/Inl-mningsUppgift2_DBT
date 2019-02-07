package shoe_shop_jdbc;

import java.util.ArrayList;

public class OutOfStock {

    private int id;
    private ArrayList<Shoe> shoes;

    public OutOfStock(int id, ArrayList<Shoe> shoes) {
        this.id = id;
        this.shoes = shoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Shoe> getShoes() {
        return shoes;
    }

    public void setShoes(ArrayList<Shoe> shoes) {
        this.shoes = shoes;
    }

}
