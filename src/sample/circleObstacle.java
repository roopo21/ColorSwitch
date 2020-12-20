package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class circleObstacle extends Obstacle {
    ArrayList<Timeline> timelines = new ArrayList<Timeline>();
    circleObstacle() {}
    circleObstacle(AnchorPane GamePlayRoot, AnchorPane invisiRoot) {
        rotateAngleModifier = 2;
        onScreen = false;
        this.GamePlayRoot = GamePlayRoot;
        this.invisiRoot = invisiRoot;
        group = new Group();
        ArrayList<Arc> allArcs = new ArrayList<Arc>();
        for(int i = 0;i <4; i++) {
            Arc test = new Arc();
            test.setFill(Paint.valueOf("ff252100"));
            test.setCenterX(640);
            test.setCenterY(298);
            test.setLength(90);
            test.setType(ArcType.OPEN);
            test.setRadiusX(120.0);
            test.setRadiusY(120);
            switch (i) {
                case 0:
                    test.setStartAngle(180);
                    test.setStroke(Paint.valueOf("#0ac3d1"));
                    break;
                case 1:
                    test.setStartAngle(90);
                    test.setStroke(Paint.valueOf("#f8126b"));
                    break;
                case 2:
                    test.setStartAngle(0);
                    test.setStroke(Paint.valueOf("#7f03f6"));
                    break;
                case 3:
                    test.setStartAngle(270);
                    test.setStroke(Paint.valueOf("0xfbd327ff"));
                    break;
            }
            test.setStrokeLineCap(StrokeLineCap.BUTT);
            test.setStrokeWidth(10.0);
            allArcs.add(test);
            group.getChildren().add(test);
        }
        group.setTranslateY(-400);
        //spawnStarAndCS();
        invisiRoot.getChildren().add(group);
        for(Arc arc: allArcs) {
            KeyValue x = new KeyValue(arc.startAngleProperty(), arc.getStartAngle(), Interpolator.LINEAR);
            KeyValue y = new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360, Interpolator.LINEAR);
            Timeline time = new Timeline(new KeyFrame(Duration.ZERO, x),
                    new KeyFrame(Duration.seconds(rotateAngleModifier), y));
            timelines.add(time);
            time.setCycleCount(Animation.INDEFINITE);
            time.play();
        }
    }
    public void stopRotate() {
        for(Timeline time: timelines) {
            time.pause();
        }
    }

    public void move(double delta, AnchorPane GamePlayRoot) throws FileNotFoundException {
        if(this.group.getParent() == GamePlayRoot) {
            this.group.setTranslateY(group.getTranslateY() - delta);
            if(group.getTranslateY() > 820) {
                this.disappear();
                group.setTranslateY(-400);
            }
        }
    }
    public void resumeRotate() {
        for(Timeline time: timelines) {
            time.play();
        }
    }

    @Override
    public void spawnStarAndCS() throws FileNotFoundException {
        star = new Star(640, group.getTranslateY()+300, GamePlayRoot, invisiRoot);
        GamePlayRoot.getChildren().add(star.star);
        GamePlayPageController.stars.add(star);
        cs= new colorSwitch(640, group.getTranslateY()+60, GamePlayRoot, invisiRoot);
        GamePlayRoot.getChildren().add(cs.cs);
        GamePlayPageController.colorSwitches.add(cs);
    }
    @Override
    public void changeSpeed() {
        if (rotateAngleModifier >= 1.6)
            this.rotateAngleModifier -= 0.2;
    }
}
