package shoe_shop_jdbc;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static shoe_shop_jdbc.shoe_shop_jdbc.repo;

public class BuyScene{

    private int ID;
    private Shoe boughtShoe;
    private OrderScene orderScene;
    private Scene scene;
    private Customer activeCustomer;
    private BorderPane root;
    private Stage primaryStage;
    private ChooseScene chooseScene;

    public BuyScene(Stage stage, Customer activeCustomer) {
//        this.scene = scene;
        this.activeCustomer = activeCustomer;
//        this.primaryStage = primaryStage;

        ArrayList<Shoe> availableShoes = repo.getShoes().stream().filter(s -> s.getStock() > 0).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Button> buttons = new ArrayList();
        BorderPane root = new BorderPane();
        ScrollPane sp = new ScrollPane();
        VBox vBox = new VBox();
        Label title = new Label("Available Shoes:");
        root.getChildren().clear();
        Button back = new Button("back");

        for (Shoe shoe : availableShoes) {
            String s = String.format("%s: %s \nsize: %s \ncolor: %s \nprice: %s kr", shoe.getBrand().getName(), shoe.getName(), shoe.getSize(), shoe.getColor(), shoe.getPrice());
            Button tempButton = new Button(s);

            tempButton.setStyle("-fx-min-height: 100px;\n"
                    + "-fx-min-width: 275px; \n");
            tempButton.setAlignment(Pos.BASELINE_LEFT);
            tempButton.setId(String.valueOf(shoe.getId()));
            buttons.add(tempButton);

            tempButton.setOnAction((event) -> {
                boughtShoe = shoe;
                getBoughtShoe();
                goToOrderScene(stage, shoe);
            });
        }

        back.setOnAction((event) -> {
            chooseScene = new ChooseScene(primaryStage, activeCustomer);
        });

        vBox.getChildren().addAll(buttons);
        sp.setContent(vBox);

        root.setCenter(sp);
        root.setTop(title);
        root.setBottom(back);
        scene = new Scene(root, 300, 500);
        stage.setScene(scene);

    }

    public void getBoughtShoe() {
        System.out.println(boughtShoe.getName());
    }

    public void goToOrderScene(Stage stage, Shoe shoe) {
        orderScene = new OrderScene(stage, activeCustomer, shoe);

    }

}
