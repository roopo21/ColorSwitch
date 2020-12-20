package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Random;

import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.paint.ImagePattern;

public class colorSwitch extends GameElements implements Serializable{
    Circle cs;
    Boolean used;
    colorSwitch() { }
    colorSwitch(double x, double y, AnchorPane myRoot, AnchorPane invisiRoot) throws FileNotFoundException {
        used = false;
        this.GamePlayRoot = myRoot;
        this.invisiRoot = invisiRoot;
        this.y = y; this.x = x;
        Image img = new Image(new FileInputStream("src/sample/Assets/ColorCircle.png"));
        ImagePattern ip = new ImagePattern(img);
        cs = new Circle();
        cs.setFill(ip);
        cs.setRadius((img.getWidth()/3));
        cs.setCenterX(x);
        cs.setCenterY(y);
    }
    public void disappear() {
        used = true;
        changeColor();
        GamePlayRoot.getChildren().remove(this.cs);
        invisiRoot.getChildren().add(this.cs);
    }
    public void move(double delta, AnchorPane GamePlayRoot) {
        if(this.cs.getParent() == GamePlayRoot) {
            this.cs.setCenterY(cs.getCenterY() - delta);
        }
    }
    void changeColor() {
        ball ball = GamePlayPageController.ball;
        String[] colors = GamePlayPageController.colors;
        Random rand = new Random();
        while(true) {
            int index = rand.nextInt(4);
            if(!ball.ball.getFill().toString().equals(colors[index])) {
                ball.ball.setFill(Paint.valueOf(colors[index]));
                break;
            }
        }
    }

}
