package cz.vse.Main;

import cz.vse.logika.IHra;
import cz.vse.uiApp.App;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static IHra hra;

    @Override
    public void start(Stage stage) throws IOException {
        App app = new App();
        app.start(stage);
    }

    public static void main(String[] args) {
        //hra = new Hra(null) ;
        launch(args);
    }
}

