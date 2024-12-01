package cz.vse.uiApp;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class OutputRegion extends Region {
    private final TextArea outputArea;

    public OutputRegion() {
        outputArea = new TextArea();
        outputArea.setEditable(false);
        StackPane stackPane = new StackPane();
        StackPane.setAlignment(outputArea, javafx.geometry.Pos.CENTER);
        stackPane.getChildren().add(outputArea);
        getChildren().add(stackPane);
    }

    public void resizeWidth(int newWidth) {
        outputArea.setPrefWidth(newWidth);
    }

    public void resizeHeight(int newHeight) {
        outputArea.setPrefHeight(newHeight);
    }

    public void print(String message) {
        outputArea.setText(message);
    }
}

