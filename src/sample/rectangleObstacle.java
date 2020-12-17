package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;

public class rectangleObstacle extends Obstacle {

    AnimationTimer timer;
    double rectWidth = 225;
    double rectHeight = 15;
    rectangleObstacle() {}
    rectangleObstacle(AnchorPane GamePlayRoot, AnchorPane invisiRoot) {
        rotateAngleModifier = 2;
        onScreen = false;
        this.GamePlayRoot = GamePlayRoot;
        this.invisiRoot = invisiRoot;
        x = 200;
        y = 100;
        group = new Group();
        Rectangle r1 = new Rectangle(x, y, rectWidth, rectHeight);
        r1.setFill(Paint.valueOf("#7f03f6"));
        Rectangle r2 = new Rectangle(x+y+(rectWidth/2), y + (rectHeight/2), rectHeight, rectWidth);
        r2.setFill(Paint.valueOf("#0ac3d1"));
        Rectangle r3 = new Rectangle(x, y + (rectHeight/2), rectHeight, rectWidth);
        r3.setFill(Paint.valueOf("#f8126b"));
        Rectangle r4 = new Rectangle(x, y+(rectWidth), rectWidth, rectHeight);
        r4.setFill(Paint.valueOf("#fbd327"));
        group.getChildren().addAll(r1,r2,r3,r4);
        group.setTranslateX(440 - (225/2));
        group.setTranslateY(-340);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                rotateAngle += rotateAngleModifier;
                group.setRotate(rotateAngle%360);
            }
        };
        timer.start();
        //this.spawnStarAndCS();
        invisiRoot.getChildren().add(group);

    }
    @Override
    public void move(double delta, AnchorPane GamePlayRoot) throws FileNotFoundException {
        if(this.group.getParent() == GamePlayRoot) {
            this.group.setTranslateY(group.getTranslateY() - delta);
            if(group.getTranslateY() > 820) {
                this.disappear();
                group.setTranslateY(-340);
            }
        }
    }

    public void stopRotate() {
        timer.stop();
    }
    public void resumeRotate() { timer.start(); }
    public void spawnStarAndCS() throws FileNotFoundException {
        star = new Star(640,group.getTranslateY()+220,GamePlayRoot, invisiRoot);
        cs = new colorSwitch(640, group.getTranslateY()-20, GamePlayRoot, invisiRoot);
        GamePlayRoot.getChildren().add(cs.cs);
        GamePlayRoot.getChildren().add(star.star);
        GamePlayPageController.stars.add(star);
        GamePlayPageController.colorSwitches.add(cs);
    }
}
