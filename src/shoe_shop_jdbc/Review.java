package shoe_shop_jdbc;

public class Review {

    private int id;
    private String comment;
    private Shoe shoe;
    private Grade grade;

    public Review(int id, String comment, Shoe shoe, Grade grade) {
        this.id = id;
        this.comment = comment;
        this.shoe = shoe;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
