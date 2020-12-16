package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
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

    private void clip()
    {   File soundeffect=new File("src/sample/Assets/Swooshsoundeffect1.mp3");
        AudioClip clip=new AudioClip(soundeffect.toURI().toString());
        clip.play(80);
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
                    clip();
                    jump();
                }
            });

            if(ball.getCenterY() > 720) {
                end();
            }
            if(ball.getCenterY() <= 359) {
                moveObstacles();
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
        tim.pause();
        for(Obstacle obs1: GamePlayPageController.obstacles) {
            obs1.stopRotate();
        }
        //endscreen
        try {
            Parent sgRoot= FXMLLoader.load(getClass().getResource("EndGamePage.fxml"));
            Scene sgScene=new Scene(sgRoot);
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(sgScene);
            window.show();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    void collision() {
        for(Obstacle obs: GamePlayPageController.obstacles) {
            try {
                if (ball.getBoundsInParent().intersects(obs.group.getBoundsInParent())) {
                    ArrayList<Shape> shapes = new ArrayList<Shape>();
                    for(Node s: obs.group.getChildren()) {
                        shapes.add((Shape)s);
                    }
                    for(Shape s: shapes) {
                        Shape intersectOrNot = Shape.intersect(ball,s);
                        if(intersectOrNot.getBoundsInLocal().getWidth() != -1) {
                            //obs.group.getChildren().remove(s);

                            if(((Arc)s).getFill().equals(ball.getFill())) {
                                System.out.println(s.getFill());
                                System.out.println(ball.getFill());
                                end();
                            }
                        }
                    }
                }
            } catch(Exception NullPointerException) { }
        }
    }
    void moveObstacles() {
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

}
