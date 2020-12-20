package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class doublePlusObstacle extends Obstacle{
    double rotateAngle2 = -rotateAngle;
    AnimationTimer timer;
    doublePlusObstacle() {}
    doublePlusObstacle(AnchorPane GamePlayRoot, AnchorPane invisiRoot) {
        rotateAngleModifier = 1.5;
        onScreen = false;
        this.GamePlayRoot = GamePlayRoot;
        this.invisiRoot = invisiRoot;
        group1 = new Group();
        group2 = new Group();
        Rectangle r1 = new Rectangle(125, 100, 75, 15);
        r1.setFill(Paint.valueOf("#7f03f6"));
        Rectangle r2 = new Rectangle(200, 107.5, 15, 75);
        r2.setFill(Paint.valueOf("#0ac3d1"));//
        Rectangle r3 = new Rectangle(200, 25, 15, 75);
        r3.setFill(Paint.valueOf("#f8126b"));
        Rectangle r4 = new Rectangle(200, 100, 82.5, 15);
        r4.setFill(Paint.valueOf("#fbd327"));//

        Rectangle _r1 = new Rectangle(107.5 -150, 100, 100, 15);
        _r1.setFill(Paint.valueOf("#7f03f6"));
        Rectangle _r2 = new Rectangle(200-150, 107.5, 15, 100);
        _r2.setFill(Paint.valueOf("#0ac3d1"));//
        Rectangle _r3 = new Rectangle(200-150, 7.5, 15, 100);
        _r3.setFill(Paint.valueOf("#f8126b"));
        Rectangle _r4 = new Rectangle(200-150, 100, 107.5, 15);
        _r4.setFill(Paint.valueOf("#fbd327"));//

        group1.getChildren().addAll(r1,r2,r3,r4);
        group1.setTranslateX(510);
        group1.setTranslateY(-200);

        group2.getChildren().addAll(_r1,_r2,_r3,_r4);
        group2.setTranslateX(510);
        group2.setTranslateY(-200);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                rotateAngle += rotateAngleModifier;
                group1.setRotate(rotateAngle%360);
                rotateAngle2 -= rotateAngleModifier;
                group2.setRotate(rotateAngle2%360);
            }
        };
        timer.start();
        //this.spawnStarAndCS();
        invisiRoot.getChildren().add(group1);
        invisiRoot.getChildren().add(group2);
    }

    public void move(double delta, AnchorPane GamePlayRoot) throws FileNotFoundException {
        if(this.group1.getParent() == GamePlayRoot) {
            this.group1.setTranslateY(group1.getTranslateY() - delta);
            if(group1.getTranslateY() > 820) {
                this.disappear();
                group1.setTranslateY(-200);
            }
            this.group2.setTranslateY(group2.getTranslateY() - delta);
            if(group2.getTranslateY() > 820) {
                group2.setTranslateY(-200);
            }
        }
    }
    public void stopRotate() {
        timer.stop();
    }
    public void resumeRotate() { timer.start(); }

    @Override
    public void spawnStarAndCS() throws FileNotFoundException {
        star = new Star(640, group1.getTranslateY() + 100, GamePlayRoot, invisiRoot);
        cs = new colorSwitch(640, group1.getTranslateY() - 80, GamePlayRoot, invisiRoot);
        GamePlayRoot.getChildren().addAll(star.star,cs.cs);
        GamePlayPageController.stars.add(star);
        GamePlayPageController.colorSwitches.add(cs);
    }

    @Override
    public void collide(ball ball) {
        if (ball.ball.getBoundsInParent().intersects(this.group1.getBoundsInParent()) || ball.ball.getBoundsInParent().intersects(this.group2.getBoundsInParent())) {
            ArrayList<Shape> shapes = new ArrayList<Shape>();
            for(Node s: this.group1.getChildren()) {
                shapes.add((Shape)s);
            }
            for(Node s: this.group2.getChildren()) {
                shapes.add((Shape)s);
            }
            for(Shape s: shapes) {
                Shape intersectOrNot = Shape.intersect(ball.ball,s);
                if(intersectOrNot.getBoundsInLocal().getWidth() != -1) {
                    //obs.group.getChildren().remove(s);

                    try {
                        if (!(s).getFill().equals(ball.ball.getFill()) && !(s).getStroke().equals(ball.ball.getFill())) {
                            //System.out.println("END");
                            ball.end();
                        }
                    }
                    catch(Exception NullPointerException) {
                        //System.out.println("END/");
                        ball.end();
                    }
                }
            }
        }
    }
    @Override
    public void disappear() throws FileNotFoundException {
        GamePlayRoot.getChildren().remove(this.group1);
        invisiRoot.getChildren().add(this.group1);
        GamePlayRoot.getChildren().remove(this.group2);
        invisiRoot.getChildren().add(this.group2);
        GamePlayPageController.spawnRandomObstacle();
        onScreen = false;
    }
    @Override
    public void appear() throws FileNotFoundException {
        this.spawnStarAndCS();
        invisiRoot.getChildren().remove(this.group1);
        GamePlayRoot.getChildren().add(this.group1);
        invisiRoot.getChildren().remove(this.group2);
        GamePlayRoot.getChildren().add(this.group2);
        this.onScreen = true;
    }
    @Override
    public void changeSpeed() {
        if(rotateAngleModifier <= 2.67)
            rotateAngleModifier += 0.33;
    }
}
