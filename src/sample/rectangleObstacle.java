package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class rectangleObstacle {
    Group group = new Group();
    double y;
    double x = 680;
    double rotateAngle = 0;
    rectangleObstacle() {}
    rectangleObstacle(AnchorPane GamePlayRoot) {
        Rectangle r1 = new Rectangle(200, 100, 225, 15);
        r1.setFill(Paint.valueOf("#7f03f6"));
        Rectangle r2 = new Rectangle(412.5, 107.5, 15, 225);
        r2.setFill(Paint.valueOf("#0ac3d1"));
        Rectangle r3 = new Rectangle(200, 107.5, 15, 225);
        r3.setFill(Paint.valueOf("#f8126b"));
        Rectangle r4 = new Rectangle(200, 325, 225, 15);
        r4.setFill(Paint.valueOf("#fbd327"));
        group.getChildren().addAll(r1,r2,r3,r4);
        group.setTranslateX(800);
        group.setTranslateY(300);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                rotateAngle +=2;
                group.setRotate(rotateAngle%360);
            }
        };
        timer.start();
        GamePlayRoot.getChildren().add(group);
    }
}
