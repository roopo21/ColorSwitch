package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.scene.paint.ImagePattern;

public class Star extends GameElements {
    Circle star;
    Star() { }
    Star(double x, double y, AnchorPane myRoot) throws FileNotFoundException {
        this.myRoot = myRoot;
        this.y = y; this.x = x;
        Image img = new Image(new FileInputStream("src/sample/Assets/star.gif"));
        ImagePattern ip = new ImagePattern(img);
        star = new Circle();
        star.setFill(ip);
        star.setRadius((img.getWidth()/15));
        star.setCenterX(x);
        star.setCenterY(y);
    }

    public void disappear(AnchorPane goHere, AnchorPane fromHere) {
        fromHere.getChildren().remove(this.star);
        goHere.getChildren().add(this.star);
    }
    public void move(double delta, AnchorPane GamePlayRoot) {
        if(this.myRoot == GamePlayRoot) {
            this.star.setCenterY(star.getCenterY() - delta);
        }
    }
}
