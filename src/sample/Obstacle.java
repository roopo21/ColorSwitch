package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Obstacle extends GameElements{
    Group group;
    Group group1, group2;
    double rotateAngle = 0;
    double rotateAngleModifier = 2;
    Star star;
    colorSwitch cs;
    Boolean onScreen;
    public void disappear() throws FileNotFoundException {
        GamePlayRoot.getChildren().remove(this.group);
        invisiRoot.getChildren().add(this.group);
        GamePlayPageController.spawnRandomObstacle();
        onScreen = false;
    }
    public void appear() throws FileNotFoundException {
        this.spawnStarAndCS();
        invisiRoot.getChildren().remove(this.group);
        GamePlayRoot.getChildren().add(this.group);
        this.onScreen = true;
    }

    public abstract void stopRotate();
    public abstract void resumeRotate();
    public abstract void spawnStarAndCS() throws FileNotFoundException;

    public void collide(ball ball) {
        if (ball.ball.getBoundsInParent().intersects(this.group.getBoundsInParent())) {
            ArrayList<Shape> shapes = new ArrayList<Shape>();
            for(Node s: this.group.getChildren()) {
                shapes.add((Shape)s);
            }
            for(Shape s: shapes) {
                Shape intersectOrNot = Shape.intersect(ball.ball,s);
                if(intersectOrNot.getBoundsInLocal().getWidth() != -1) {
                    try {
                        if (!(s).getFill().equals(ball.ball.getFill()) && !(s).getStroke().equals(ball.ball.getFill())) {
                            ball.end();
                        }
                    }
                    catch(Exception NullPointerException) {
                        ball.end();
                    }
                }
            }
        }
    }

    public void changeSpeed() {
        if(rotateAngleModifier <= 3)
            rotateAngleModifier +=0.5;
    }
}
