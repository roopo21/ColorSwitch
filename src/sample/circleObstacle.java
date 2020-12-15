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

import java.util.ArrayList;

public class circleObstacle {
    double y;
    double x = 680;
    double rotateAngle = 0;
    circleObstacle() {}
    circleObstacle(AnchorPane GamePlayRoot) {
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
                    test.setStroke(Paint.valueOf("#fbd327"));
                    break;
            }
            test.setStrokeLineCap(StrokeLineCap.BUTT);
            test.setStrokeWidth(10.0);
            allArcs.add(test);
            GamePlayRoot.getChildren().add(test);
        }
        for(Arc arc: allArcs) {
            KeyValue x = new KeyValue(arc.startAngleProperty(), arc.getStartAngle(), Interpolator.LINEAR);
            KeyValue y = new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360, Interpolator.LINEAR);
            Timeline time = new Timeline(new KeyFrame(Duration.ZERO, x),
                    new KeyFrame(Duration.seconds(2), y));
            time.setCycleCount(Animation.INDEFINITE);
            time.play();
        }
    }
}
