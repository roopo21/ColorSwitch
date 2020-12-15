package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class doublePlusObstacle {
    Group group1 = new Group();
    Group group2 = new Group();
    double y;
    double x = 680;
    double rotateAngle1 = 0;
    double rotateAngle2 = 0;
    doublePlusObstacle() {}
    doublePlusObstacle(AnchorPane GamePlayRoot) {
        Rectangle r1 = new Rectangle(125, 100, 75, 15);
        r1.setFill(Paint.valueOf("#7f03f6"));
        Rectangle r2 = new Rectangle(200, 107.5, 15, 75);
        r2.setFill(Paint.valueOf("#0ac3d1"));
        Rectangle r3 = new Rectangle(200, 25, 15, 75);
        r3.setFill(Paint.valueOf("#f8126b"));
        Rectangle r4 = new Rectangle(200, 100, 82.5, 15);
        r4.setFill(Paint.valueOf("#fbd327"));

        Rectangle _r1 = new Rectangle(107.5 -150, 100, 100, 15);
        _r1.setFill(Paint.valueOf("#7f03f6"));
        Rectangle _r2 = new Rectangle(200-150, 107.5, 15, 100);
        _r2.setFill(Paint.valueOf("#0ac3d1"));
        Rectangle _r3 = new Rectangle(200-150, 7.5, 15, 100);
        _r3.setFill(Paint.valueOf("#f8126b"));
        Rectangle _r4 = new Rectangle(200-150, 100, 107.5, 15);
        _r4.setFill(Paint.valueOf("#fbd327"));

        group1.getChildren().addAll(r1,r2,r3,r4);
        group1.setTranslateX( 150);
        group1.setTranslateY(300);

        group2.getChildren().addAll(_r1,_r2,_r3,_r4);
        group2.setTranslateX( 150);
        group2.setTranslateY(300);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                rotateAngle1 +=1.5;
                group1.setRotate(rotateAngle1%360);
                rotateAngle2 -=1.5;
                group2.setRotate(rotateAngle2%360);
            }
        };
        timer.start();
        GamePlayRoot.getChildren().add(group1);
        GamePlayRoot.getChildren().add(group2);
    }
}
