package shoe_shop_jdbc;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static shoe_shop_jdbc.shoe_shop_jdbc.repo;

public class OrderScene {

    private ArrayList<Shoe> shoes;
    private Scene scene;
    private ChooseScene chooseScene;

    public OrderScene(Stage stage, Customer activeCustomer, Shoe shoe) {

        ArrayList<Order> orders = activeCustomer.getOrders();
        BorderPane root = new BorderPane();
        ScrollPane sp = new ScrollPane();
        VBox vBox = new VBox();

        Label topText = new Label("Add to non expedited order or create new order:");

        for (Order order : orders) {

            if (!order.isExpedited()) {
                String date = order.getOrderdate().toString();
                Button tempButton = new Button(date);

                tempButton.setStyle("-fx-min-height: 100px;\n"
                        + "-fx-min-width: 275px; \n");
                tempButton.setAlignment(Pos.BASELINE_LEFT);

                tempButton.setOnAction((event) -> {
                    repo.addToCart(shoe.getId(), activeCustomer.getId(), order.getId());
                    buyDone(stage, activeCustomer);
                });

                vBox.getChildren().add(tempButton);
            }
        }
        Button newOrder = new Button("Make new Order");
        newOrder.setStyle("-fx-min-height: 100px;\n"
                + "-fx-min-width: 275px; \n");
        newOrder.setAlignment(Pos.BASELINE_LEFT);

        newOrder.setOnAction((event) -> {
            repo.addToCart(shoe.getId(), activeCustomer.getId(), -1);
            buyDone(stage, activeCustomer);
        });

        vBox.getChildren().add(newOrder);
        sp.setContent(vBox);
        root.setTop(topText);
        root.setCenter(sp);

        scene = new Scene(root, 300, 500);
        stage.setScene(scene);

    }

    private void buyDone(Stage stage, Customer activeCustomer) {
        System.out.println("thank you for shopping");
        repo.updateRepo();
        activeCustomer = repo.getCustomers().get(activeCustomer.getId() - 1);
        chooseScene = new ChooseScene(stage, activeCustomer);
    }
}
