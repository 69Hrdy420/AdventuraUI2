package cz.vse.uiApp;

import cz.vse.Images.Images;
import cz.vse.Observers.ItemObserver;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ItemRegionBase extends Region implements ItemObserver {
    public ItemRegionBase() {
        items = new VBox(7);
        selectedItem = "";
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(items);
        getChildren().add(stackPane);
    }

    public void setWidth(double width) {
        items.setMinWidth(width);
        items.setMaxWidth(width);
        super.setMinWidth(width);
        super.setMaxWidth(width);
    }

    public String getSelectedItem() {
        String result = selectedItem;
        selectedItem = "";
        return result;
    }

    @Override
    public void update(List<String> items) {
        setItems(items);
    }

    protected VBox items;
    protected String selectedItem;

    private void setItems(List<String> newItems) {
        items.getChildren().clear();
        for (String item : newItems) {
            ImageView exitImage = Images.getInstance().getImage(item);
            if (exitImage != null) {
                exitImage.setFitWidth(items.getMinWidth() / 4);
                exitImage.setFitHeight(exitImage.getFitWidth());

                Label exitText = new Label(item);
                exitText.setAlignment(javafx.geometry.Pos.CENTER);

                VBox labelBox = new VBox();
                labelBox.setAlignment(javafx.geometry.Pos.CENTER);
                labelBox.getChildren().add(exitText);

                HBox exitBox = new HBox(10);
                exitBox.setStyle("-fx-background-color: #D3D3D3;");

                exitBox.setOnMouseClicked(event ->
                {
                    for (Node node : items.getChildren()) {
                        if (node instanceof HBox) {
                            node.setStyle("-fx-background-color: #D3D3D3;");
                        }
                    }
                    exitBox.setStyle("-fx-background-color: #4682B4;");
                    selectedItem = exitText.getText();
                });

                exitBox.getChildren().addAll(exitImage, labelBox);
                items.getChildren().add(exitBox);
            }
        }
    }
}
