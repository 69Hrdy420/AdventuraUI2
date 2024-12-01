package uiApp;

public class BatohRegion extends ItemRegionBase {
    public void hide() {
        selectedItem = "";
        items.getChildren().clear();
    }

    public void setHeight(double width) {
        items.setMaxHeight(width);
        items.setMaxHeight(width);
        super.setMinHeight(width);
        super.setMaxHeight(width);
    }
}
