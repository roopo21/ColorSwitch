package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ball {
    double y;
    double gravityY;
    Circle ball;
    AnchorPane GamePlayRoot;
    int ticks;
    Timeline tim = new Timeline();
    Scene mainScene;

    ball(AnchorPane GamePlayRoot, Scene mainScene) {
        this.GamePlayRoot = GamePlayRoot;
        this.mainScene = mainScene;
        ball = new Circle();
        ball.setRadius(13);
        ball.setCenterX(640);
        ball.setCenterY(550);
        ball.setFill(Paint.valueOf("#fbd327"));
        GamePlayRoot.getChildren().add(ball);
        start();
        gravity();
    }

    void start() {
        tim.pause();
        mainScene.setOnKeyReleased(k -> {
            String code = k.getCode().toString();
            if(code == "UP") {
                tim.play();
            }
        });
    }

    void gravity() {
        KeyFrame kf=new KeyFrame(Duration.millis(20), e -> {
            ticks++;
            if(ticks%3==0&&gravityY<7) {
                gravityY=gravityY+1;

            }
            double y=(int)ball.getCenterY()+gravityY;
            ball.setCenterY(y);
            mainScene.setOnKeyReleased(k -> {
                String code = k.getCode().toString();
                if(code=="UP") {
                    jump();
                }
            });

        });
        tim.getKeyFrames().add(kf);
        tim.setCycleCount(Animation.INDEFINITE);

    }

    void jump() {
        if(gravityY < 0) {
            gravityY = 0;
        }
        gravityY -= 10;
    }
}
