package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ball {
    double y;
    double gravityY;
    Circle ball;
    AnchorPane GamePlayRoot;
    int ticks;
    Timeline tim = new Timeline();
    Scene mainScene;

    ball(AnchorPane GamePlayRoot, Scene mainScene) throws FileNotFoundException {
        this.GamePlayRoot = GamePlayRoot;
        this.mainScene = mainScene;
        ball = new Circle();
        ball.setRadius(13);
        ball.setCenterX(640);
        ball.setCenterY(550);
        ball.setFill(Paint.valueOf("0xfbd327ff"));
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

    void gravity() throws FileNotFoundException {
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

            if(ball.getCenterY() > 720) {
                end();
            }
            if(ball.getCenterY() <= 359) {
                try {
                    moveObstacles();
                }
                catch(Exception FileNotFoundException ) { }
            }

            collision();
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

    public void end() {
        pause();
        //endscreen
    }
    public void pause() {
        tim.pause();
        for(Obstacle obs1: GamePlayPageController.obstacles) {
            obs1.stopRotate();
        }
    }

    public void resume() {
        tim.play();
        for(Obstacle obs1: GamePlayPageController.obstacles) {
            obs1.resumeRotate();
        }
    }

    void collision() {
        for(Obstacle obs: GamePlayPageController.obstacles) {
            obs.collide(this);
        }
        for(Star s: GamePlayPageController.stars) {
            if(ball.getBoundsInParent().intersects(s.star.getBoundsInParent()) && !s.added) {
                s.disappear();
            }
        }
        for(colorSwitch cs: GamePlayPageController.colorSwitches) {
            if(ball.getBoundsInParent().intersects(cs.cs.getBoundsInParent()) && !cs.used) {
                cs.disappear();
            }
        }
    }
    void moveObstacles() throws FileNotFoundException {
        double delta = ball.getCenterY() - 360;
        ball.setCenterY(360);
        for(int i = 0; i<GamePlayPageController.obstacles.size(); i++) {
            Obstacle obs = GamePlayPageController.obstacles.get(i);
            obs.move(delta, GamePlayRoot);
        }
        for(Star star: GamePlayPageController.stars) {
            star.move(delta, GamePlayRoot);
        }
        for(colorSwitch cs: GamePlayPageController.colorSwitches) {
            cs.move(delta, GamePlayRoot);
        }

    }
    void resetPosition() {
        ball.setCenterY(550);

    }

}
