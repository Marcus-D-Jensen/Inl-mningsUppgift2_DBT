/*
 * Javautveckling 2018
 */
package shoe_shop_jdbc;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class shoe_shop_jdbc extends Application {
    private Stage window;
    private Scene startingScene;

    public static String name;
    public static String pw;
    public static Repository repo;
    public Shoe boughtShoe;
    
    private Label pwLabel, usernameLabel, status;
    private TextField username;
    private PasswordField password;
    private ChooseScene chooseScene;

    private VBox vbox;
    private Customer activeCustomer;

    @Override
    public void start(Stage primaryStage) {

        vbox = new VBox();
        BorderPane root = new BorderPane();

        Button btn = new Button();
        btn.setText("Login");

        usernameLabel = new Label("Username:");
        username = new TextField();
        pwLabel = new Label("Password");
        password = new PasswordField();
        status = new Label("");

        usernameLabel.setMinWidth(150);
        pwLabel.setMinWidth(150);
        username.setMinWidth(150);
        password.setMinWidth(150);

        vbox.getChildren().add(usernameLabel);
        vbox.getChildren().add(username);
        vbox.getChildren().add(pwLabel);
        vbox.getChildren().add(password);
        vbox.getChildren().add(btn);
        vbox.getChildren().add(status);

        root.setCenter(vbox);

        Scene scene = new Scene(root, 300, 500);

        primaryStage.setTitle("Login Shoe Shop");
        primaryStage.setScene(scene);
        primaryStage.show();

        btn.setOnAction((event) -> {
            loginButtonPressed(primaryStage);
        });
        password.setOnAction((event) -> {
            loginButtonPressed(primaryStage);
        });
    }

    public void loginButtonPressed(Stage primaryStage) {
        name = username.getText();
        pw = password.getText();
        if (checkLogin()) {
            chooseScene = new ChooseScene(primaryStage, activeCustomer);

        } else {
            pwLabel.setStyle("-fx-text-fill: RED;");
            usernameLabel.setStyle("-fx-text-fill: RED;");
            status.setText("Incorrect username or password");
            username.setText("");
            password.setText("");
        }
    }

    public boolean checkLogin() {
        ArrayList<Customer> customers = repo.getCustomers();
        try {
            activeCustomer = customers.stream().filter(c -> c.getName().equalsIgnoreCase(name)).filter(c -> c.getPassword().equals(pw)).findAny().get();
            System.out.println("login successfull");
            System.out.println("Welcome " + activeCustomer.getName() + "!");
            return true;
        } catch (NoSuchElementException ex) {
            System.out.println("Password or username not correct");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        repo = new Repository();
        launch(args);
    }
}
