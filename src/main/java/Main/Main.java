package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import logika.IHra;
import uiApp.App;

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

