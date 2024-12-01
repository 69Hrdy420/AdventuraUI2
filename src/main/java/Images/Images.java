package Images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class Images {
    private static Images instance;
    private final Map<String, Image> imageMap;
    private final String imagePath;

    private Images() {
        imageMap = new HashMap<>();
        imagePath = "file:///C:/Users/jan/Downloads/";

        add("domecek", "jpg");
        add("les", "jpg");
        add("hluboky_les", "jpeg");
        add("chaloupka", "jpg");
        add("jeskyne", "jpg");
        add("maliny", "jpg");
        add("strom", "jpeg");
    }

    public static Images getInstance() {
        if (instance == null) {
            instance = new Images();
        }
        return instance;
    }

    public ImageView getImage(String key) {
        Image image = imageMap.get(key);
        if (image == null) {
            return null;
        }
        return new ImageView(image);
    }

    public void add(String name, String extension) {
        String fullPath = imagePath + name + "." + extension;
        imageMap.put(name, new Image(fullPath));
    }
}
