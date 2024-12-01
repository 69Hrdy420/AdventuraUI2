package cz.vse.uiApp;

import cz.vse.Images.Images;
import cz.vse.Observers.MapObserver;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MapRegion extends Region implements MapObserver {
    private final Map<String, ImageView> plan;
    private final Circle pos;

    public MapRegion() {
        pos = new Circle();
        pos.setFill(Color.RED);
        pos.setRadius(6);
        plan = new HashMap<>();
    }

    private void addProstor(String nazev, Pair<Integer, Integer> pozice) {
        double currentWidth = getWidth();
        double currentHeight = getHeight();
        double newImageSize = newImageSize();

        ImageView imageView = Images.getInstance().getImage(nazev);

        if (imageView != null) {
            imageView.setX(pozice.getKey());
            imageView.setY(pozice.getValue());
            imageView.setFitWidth(newImageSize);
            imageView.setFitHeight(newImageSize);
            plan.put(nazev, imageView);
            getChildren().add(imageView);
        }
    }

    public void initialize() {
        Integer y = (int) (getHeight() / 3);
        Integer x = (int) (getWidth() / 16);
        Integer spaceX = (int) (newImageSize() * 4 / 3);

        addProstor("domecek", new Pair(x, y));
        updatePosition("domecek");
        x += spaceX;
        addProstor("les", new Pair(x, y));
        x += spaceX;
        addProstor("hluboky_les", new Pair(x, y));
        x += spaceX;

        y = (int) (getHeight() / 9);
        addProstor("chaloupka", new Pair(x, y));
        y = (int) (getHeight() * 5 / 9);
        addProstor("jeskyne", new Pair(x, y));

        getChildren().add(pos);
    }

    public void resize() {
        if (plan.size() != 0) {
            double width = getWidth();
            double height = getHeight();

            Map.Entry<String, ImageView> firstEntry = plan.entrySet().iterator().next();
            double newImageSize = newImageSize();
            double minOffsetX1 = firstEntry.getValue().getX();
            double maxOffsetX2 = minOffsetX1;


            double ratio = newImageSize / firstEntry.getValue().getFitWidth();
            if (ratio != 1.d) {
                pos.setCenterX(pos.getCenterX() * ratio);
                pos.setCenterY(pos.getCenterY() * ratio);
                for (Map.Entry<String, ImageView> entry : plan.entrySet()) {
                    ImageView imageView = entry.getValue();
                    imageView.setFitWidth(newImageSize);
                    imageView.setFitHeight(newImageSize);
                    imageView.setX(imageView.getX() * ratio);
                    imageView.setY(imageView.getY() * ratio);
                }
            }
        }
    }

    private double newImageSize() {
        return Math.min(getWidth() / (18.d / 3.d), getHeight() / 3.d);
    }

    @Override
    public void updatePosition(String newPosition) {
        ImageView imageView = plan.get(newPosition);

        if (imageView != null) {
            double center = imageView.getFitWidth() / 2;
            pos.setCenterX(imageView.getX() + center);
            pos.setCenterY(imageView.getY() + center);
        }
    }
}

