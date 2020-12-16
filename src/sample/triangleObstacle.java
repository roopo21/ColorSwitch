package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.Random;

public class triangleObstacle extends Obstacle {
    AnimationTimer timer;
    triangleObstacle() {}
    triangleObstacle(AnchorPane GamePlayRoot) {
        group = new Group();
        String[] colors = new String[4];
        colors[0] = "0x7f03f6ff";
        colors[1] = "0x0ac3d1ff";
        colors[2] = "0xf8126bff";
        colors[3] = "0xfbd327ff";
        String ballColor = GamePlayPageController.ball.ball.getFill().toString();
        //System.out.println(ballColor);
        Rectangle r1 = new Rectangle(100, 100, 200, 12);
        r1.setFill(Paint.valueOf(ballColor));
        r1.setRotate(0);
        Rectangle r2 = new Rectangle(52, 183, 200, 12);
        setColor(r2,colors, ballColor);
        r2.setRotate(240);
        Rectangle r3 = new Rectangle(148, 183, 200, 12);
        setColor(r3,colors, ballColor);
        r3.setRotate(300);
        group.getChildren().addAll(r1,r2,r3);
        group.setTranslateX(800);
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
    void setColor(Rectangle r, String[] colors, String ballColor) {
        Random rand = new Random();
        while(true) {
            int next = rand.nextInt(4);
            if(ballColor.equals(colors[next])) {
                colors[next] = "lol";
                continue;
            }
            if(!colors[next].equals("lol")) {
                //System.out.println("test" + ballColor +" and " + colors[next]);
                r.setFill(Paint.valueOf(colors[next]));
                colors[next] = "lol";
                break;
            }
        }
    }

    public void move(double delta, AnchorPane GamePlayRoot) {
        if(this.group.getParent() == GamePlayRoot) {
            this.group.setTranslateY(group.getTranslateY() - delta);
            if(group.getTranslateY() > 900) {
                group.setTranslateY(100);
            }
        }
    }
    public void stopRotate() {
        timer.stop();
    }
}
