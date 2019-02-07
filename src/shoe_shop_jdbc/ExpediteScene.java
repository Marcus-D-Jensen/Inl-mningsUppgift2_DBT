package shoe_shop_jdbc;

import java.util.ArrayList;
import java.util.Collection;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static shoe_shop_jdbc.shoe_shop_jdbc.repo;

public class ExpediteScene {

    private ChooseScene chooseScene;
//    private Customer activeCustomer;

    public ExpediteScene(Stage stage, Customer activeCustomer) {
        ArrayList<Order> orders = activeCustomer.getOrders();
        BorderPane root = new BorderPane();
        ScrollPane sp = new ScrollPane();
        VBox vBox = new VBox();

        for (Order order : orders) {

            if (!order.isExpedited()) {
                String buttonText = order.getId() + "\n";
                buttonText += order.getOrderdate().toString();

                Button tempButton = new Button(buttonText);

                tempButton.setStyle("-fx-min-height: 100px;\n"
                        + "-fx-min-width: 275px; \n");
                tempButton.setAlignment(Pos.BASELINE_LEFT);

                tempButton.setOnAction((event) -> {
                    buttonPressed(order, activeCustomer, stage);
                });

                vBox.getChildren().add(tempButton);
            }
        }

        sp.setContent(vBox);
        root.setCenter(sp);
        Scene scene = new Scene(root, 300, 500);
        stage.setScene(scene);

    }

    private void buttonPressed(Order order, Customer activeCustomer, Stage stage) {
        System.out.println("expedited order " + order.getId());
        repo.expediteOrder(order.getId());
        System.out.println("thank you for shopping");
        repo.updateRepo();
        activeCustomer = repo.getCustomers().get(activeCustomer.getId() - 1);

        chooseScene = new ChooseScene(stage, activeCustomer);
    }
}
