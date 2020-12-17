package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.scene.paint.ImagePattern;

public class Star extends GameElements {
    Circle star;
    Boolean added;
    Star() { }
    Star(double x, double y, AnchorPane myRoot, AnchorPane invisiRoot) throws FileNotFoundException {
        added = false;
        this.GamePlayRoot = myRoot;
        this.invisiRoot = invisiRoot;
        this.y = y; this.x = x;
        Image img = new Image(new FileInputStream("src/sample/Assets/star.gif"));
        ImagePattern ip = new ImagePattern(img);
        star = new Circle();
        star.setFill(ip);
        star.setRadius((img.getWidth()/15));
        star.setCenterX(x);
        star.setCenterY(y);
    }
    @Override
    public void disappear() {
        GamePlayPageController.currScore++;
        if(GamePlayPageController.currScore%5 == 0 && GamePlayPageController.currScore > 0) {
            for(Obstacle obs: GamePlayPageController.obstacles) {
                obs.changeSpeed();
            }
        }
        added = true;
        GamePlayRoot.getChildren().remove(this.star);
        invisiRoot.getChildren().add(this.star);
    }
    public void move(double delta, AnchorPane GamePlayRoot) {
        if(this.star.getParent() == GamePlayRoot) {
            this.star.setCenterY(star.getCenterY() - delta);
        }
    }
}
