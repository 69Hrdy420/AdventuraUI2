module cz.vse.adventuraui {
    requires javafx.controls;
    requires javafx.fxml;
    //requires org.jetbrains.annotations;
    requires java.desktop;


    opens uiApp to javafx.fxml;
    exports uiApp;
    exports Main;
    opens Main to javafx.fxml;
    exports Images;
    opens Images to javafx.fxml;
}