package uiApp;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;

import java.io.IOException;

public class App extends Application {
    private IHra hra;
    private PrikazRegion prikaz;
    private MapRegion mapRegion;
    private ExitsRegion exitsRegion;
    private ItemsRegion itemsRegion;
    private BatohRegion batohRegion;
    private OutputRegion outputRegion;
    // For updating prikaz variable, that this member contains
    private VBox leftSide;

    private void newGame() {
        hra = new Hra(exitsRegion, mapRegion, itemsRegion, batohRegion);
        prikaz = new PrikazRegion(hra, outputRegion, exitsRegion, itemsRegion, batohRegion);
        hra.setEndObserver(prikaz);
        leftSide.getChildren().removeLast();
        leftSide.getChildren().add(prikaz);
        prikaz.setWidth(200);
        mapRegion.updatePosition("domecek");
        outputRegion.print(hra.vratUvitani());
    }

    private void hint() {

    }

    private MenuBar createMenu() {
        MenuItem newGame = new MenuItem("Nová Hra");
        MenuItem hint = new MenuItem("Nápověda");

        newGame.setOnAction(event -> newGame());
        hint.setOnAction(event -> hint());

        Menu menu = new Menu("Možosti");
        menu.getItems().addAll(newGame, hint);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);

        BorderPane root = new BorderPane();

        return menuBar;
    }

    private MapRegion createMap() {
        MapRegion map = new MapRegion();
        map.setStyle("-fx-background-color: lightblue;");
        map.setPrefHeight(200);
        map.widthProperty().addListener((observable, oldValue, newValue) -> {
            map.resize();
        });
        map.heightProperty().addListener((observable, oldValue, newValue) -> {
            map.resize();
        });

        return map;
    }

    private OutputRegion craeteOutput() {
        OutputRegion output = new OutputRegion();
        output.setStyle("-fx-background-color: blue;");
        HBox.setHgrow(output, Priority.ALWAYS);
        output.widthProperty().addListener((observable, oldValue, newValue) -> {
            output.resizeWidth(newValue.intValue());
        });
        output.heightProperty().addListener((observable, oldValue, newValue) -> {
            output.resizeHeight(newValue.intValue());
        });
        return output;
    }

    private ItemsRegion createItems() {
        ItemsRegion items = new ItemsRegion();
        items.setStyle("-fx-background-color: yellow;");
        items.setWidth(200);
        items.heightProperty().addListener((observable, oldValue, newValue) -> {
            items.resizeHeight(newValue.intValue());
        });
        return items;
    }

    private BatohRegion createBatoh() {
        BatohRegion batoh = new BatohRegion();
        batoh.setStyle("-fx-background-color: red;");
        batoh.setWidth(200);
        batoh.setHeight(50);
        return batoh;
    }

    private ExitsRegion createExits() {
        ExitsRegion exits = new ExitsRegion();
        exits.setWidth(200);
        exits.heightProperty().addListener((observable, oldValue, newValue) -> {
            exits.resizeHeight(newValue.intValue());
        });
        return exits;
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize variables
        mapRegion = createMap();
        outputRegion = craeteOutput();
        itemsRegion = createItems();
        batohRegion = createBatoh();
        exitsRegion = createExits();
        MenuBar menu = createMenu();
        hra = new Hra(exitsRegion, mapRegion, itemsRegion, batohRegion);
        prikaz = new PrikazRegion(hra, outputRegion, exitsRegion, itemsRegion, batohRegion);
        hra.setEndObserver(prikaz);
        prikaz.setWidth(200);

        // Create layout.
        HBox lowerPart = new HBox(10);
        lowerPart.setStyle("-fx-background-color: lightgreen;");
        lowerPart.setPrefHeight(200);
        lowerPart.setAlignment(Pos.CENTER);

        leftSide = new VBox(10);
        VBox.setVgrow(exitsRegion, Priority.ALWAYS);
        leftSide.getChildren().addAll(exitsRegion, prikaz);

        VBox rightSide = new VBox(10);
        VBox.setVgrow(itemsRegion, Priority.ALWAYS);
        rightSide.getChildren().addAll(itemsRegion, batohRegion);

        lowerPart.getChildren().addAll(leftSide, outputRegion, rightSide);

        SplitPane vertical = new SplitPane();
        vertical.setOrientation(Orientation.VERTICAL);
        vertical.getItems().addAll(mapRegion, lowerPart);

        VBox app = new VBox();
        app.getChildren().addAll(menu, vertical);
        VBox.setVgrow(vertical, Priority.ALWAYS);

        Scene scene = new Scene(app, 600, 425);
        stage.setTitle("Karkulka");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(425);
        stage.show();
        mapRegion.initialize();
    }
}