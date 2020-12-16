package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.scene.paint.ImagePattern;

public class colorSwitch extends GameElements{
    Circle cs;
    colorSwitch() { }
    colorSwitch(double x, double y, AnchorPane myRoot) throws FileNotFoundException {
        this.myRoot = myRoot;
        this.y = y; this.x = x;
        Image img = new Image(new FileInputStream("src/sample/Assets/ColorCircle.png"));
        ImagePattern ip = new ImagePattern(img);
        cs = new Circle();
        cs.setFill(ip);
        cs.setRadius((img.getWidth()/3));
        cs.setCenterX(x);
        cs.setCenterY(y);
    }
    public void disappear(AnchorPane goHere, AnchorPane fromHere) {
        fromHere.getChildren().remove(this.cs);
        goHere.getChildren().add(this.cs);
    }
    public void move(double delta, AnchorPane GamePlayRoot) {
        if(this.myRoot == GamePlayRoot) {
            this.cs.setCenterY(cs.getCenterY() - delta);
        }
    }

}
