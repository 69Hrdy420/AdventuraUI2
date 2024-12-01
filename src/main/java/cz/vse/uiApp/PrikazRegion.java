package cz.vse.uiApp;

import cz.vse.Observers.EndObserver;
import cz.vse.logika.IHra;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PrikazRegion extends Region implements EndObserver {
    private final Map<String, Supplier<String>> combinedPrikazy;
    private final ComboBox<String> inputBox;
    private final Button confirmButton;
    private final ItemRegionBase exitsRegion;
    private final ItemRegionBase itemsRegion;
    private final BatohRegion batohRegion;
    private final OutputRegion outputRegion;
    private boolean playerWon = false;
    private boolean displayPlayerWon = false;
    private boolean playerPlayes = true;

    public PrikazRegion(IHra hra, OutputRegion output, ItemRegionBase exitsRegion, ItemRegionBase itemsRegion, BatohRegion batohRegion) {
        this.exitsRegion = exitsRegion;
        this.itemsRegion = itemsRegion;
        this.batohRegion = batohRegion;
        this.outputRegion = output;

        combinedPrikazy = new HashMap<>();
        combinedPrikazy.put("jdi", this::getJdi);
        combinedPrikazy.put("seber", this::getSeber);
        combinedPrikazy.put("poloz", this::getPoloz);

        inputBox = new ComboBox<>();
        confirmButton = new Button("Provést");
        confirmButton.setMaxWidth(70);
        confirmButton.setMinWidth(70);
        confirmButton.setOnAction(e -> {
            if (playerPlayes) {
                String result = hra.zpracujPrikaz(getText());
                if (!displayPlayerWon) {
                    output.print(result);
                } else {
                    displayPlayerWon = false;
                }
            } else {
                output.print("Ukočil jsi hru. Začni novou hru, pokud chceš stále hrát.\nNovou hru můžeš začít přes menu bar -> Nová Hra");
            }
        });

        for (String prikaz : hra.getPrikazy()) {
            inputBox.getItems().add(prikaz);
        }
        inputBox.setValue(hra.getPrikazy().get(0));

        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(inputBox, confirmButton);

        StackPane stackPane = new StackPane();
        StackPane.setAlignment(hbox, javafx.geometry.Pos.CENTER);
        stackPane.getChildren().add(hbox);
        getChildren().add(stackPane);
    }

    public String getText() {
        String prikaz = inputBox.getValue();
        Supplier<String> function = combinedPrikazy.get(prikaz);
        if (function != null) {
            prikaz = function.get();
        }
        inputBox.getEditor().clear();
        batohRegion.hide();
        return prikaz;
    }

    public void setWidth(double width) {
        inputBox.setMinWidth(width - 5 - confirmButton.getMinWidth());
        inputBox.setMaxWidth(width - 5 - confirmButton.getMinWidth());
        super.setMinWidth(width);
        super.setMaxWidth(width);
    }

    private String getJdi() {
        return "jdi " + exitsRegion.getSelectedItem();
    }

    private String getSeber() {
        return "seber " + itemsRegion.getSelectedItem();
    }

    private String getPoloz() {
        return "poloz " + batohRegion.getSelectedItem();
    }

    @Override
    public void gameEndedByCommand() {
        playerPlayes = false;
    }

    @Override
    public void gameEndedByVictory() {
        outputRegion.print("Gratuluji k úspěšnému dokončení hry.\nPořád se tu ale můžeš jen tak procházet.");
        if (!playerWon) {
            playerWon = true;
            displayPlayerWon = true;
        }
    }
}

