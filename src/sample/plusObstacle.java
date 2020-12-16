package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class plusObstacle extends Obstacle {
    AnimationTimer timer;
    plusObstacle() {}
    plusObstacle(AnchorPane GamePlayRoot) {
        group = new Group();
        Rectangle r1 = new Rectangle(107.5, 100, 100, 15);
        r1.setFill(Paint.valueOf("#7f03f6"));
        Rectangle r2 = new Rectangle(200, 107.5, 15, 100);
        r2.setFill(Paint.valueOf("#0ac3d1"));
        Rectangle r3 = new Rectangle(200, 7.5, 15, 100);
        r3.setFill(Paint.valueOf("#f8126b"));
        Rectangle r4 = new Rectangle(200, 100, 107.5, 15);
        r4.setFill(Paint.valueOf("#fbd327"));
        group.getChildren().addAll(r1,r2,r3,r4);
        group.setTranslateX( 100);
        group.setTranslateY(100);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                rotateAngle +=2;
                group.setRotate(rotateAngle%360);
            }
        };
        timer.start();
        GamePlayRoot.getChildren().add(group);
    }
    public void move(double delta, AnchorPane GamePlayRoot) {
        if(this.group.getParent() == GamePlayRoot) {
            this.group.setTranslateY(group.getTranslateY() - delta);
            if(group.getTranslateY() > 900) {
                group.setTranslateY(-100);
            }
        }
    }
    public void stopRotate() {
        timer.stop();
    }
}
