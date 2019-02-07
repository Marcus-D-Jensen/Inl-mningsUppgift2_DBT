package shoe_shop_jdbc;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseScene {

    private BuyScene buyScene;
    private ExpediteScene epediteScene;

    public ChooseScene(Stage stage, Customer activeCustomer) {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();

        Button bExpediteOrder = new Button("Expidite Order");
        Button bBuy = new Button("Buy new shoes");

        bExpediteOrder.setStyle("-fx-min-width: 300px;\n"
                + "-fx-min-height: 50px;");
        bBuy.setStyle("-fx-min-width: 300px;\n"
                + "-fx-min-height: 50px;");

        bBuy.setOnAction((event) -> {
            buyScene = new BuyScene(stage, activeCustomer);
        });

        bExpediteOrder.setOnAction((event) -> {
            epediteScene = new ExpediteScene(stage, activeCustomer);
        });

        vbox.getChildren().add(bBuy);
        vbox.getChildren().add(bExpediteOrder);

        root.setCenter(vbox);
        Scene scene = new Scene(root, 300, 500);
        stage.setScene(scene);

    }
}
