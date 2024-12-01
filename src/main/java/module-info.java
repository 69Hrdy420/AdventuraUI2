module cz.vse.adventuraui {
    requires javafx.controls;
    requires javafx.fxml;
    //requires org.jetbrains.annotations;
    requires java.desktop;

    opens cz.vse.uiApp to javafx.fxml;
    exports cz.vse.uiApp;

    opens cz.vse.Main to javafx.fxml;
    exports cz.vse.Main;

    exports cz.vse.Images;
    opens cz.vse.Images to javafx.fxml;

    exports cz.vse.logika;
    opens cz.vse.logika to javafx.fxml;

    exports cz.vse.Observers;
    opens cz.vse.Observers to javafx.fxml;

    exports cz.vse.uiText;
    opens cz.vse.uiText to javafx.fxml;
}