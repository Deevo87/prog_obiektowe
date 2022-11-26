package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class GuiElementBox {
    VBox vbox = new VBox(0);

    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        Image image = element.getMapElImage();
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Label position;
        if (element.getClass().equals(Animal.class)) {
            position = new Label(((Animal) element).getPosition().toString());
        } else {
            position = new Label("grass");
        }
        this.vbox.getChildren().addAll(imageView, position);
        this.vbox.setAlignment(Pos.CENTER);
    }
    public VBox boxGetter() {
        return this.vbox;
    }

}
