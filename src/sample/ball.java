package sample;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    Stage bigStage;
    //public static int score=0;
    GamePlayPageController gamePlay;
    ImageView finger;
    ball(AnchorPane GamePlayRoot, Scene mainScene,GamePlayPageController gamePlay,ImageView fing) throws FileNotFoundException {
        this.GamePlayRoot = GamePlayRoot;
        this.mainScene = mainScene;
        this.finger=fing;

        this.gamePlay=gamePlay;
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
    {
        File soundeffect=new File("src/sample/Assets/Swooshsoundeffect1.mp3");
        AudioClip clip=new AudioClip(soundeffect.toURI().toString());
        clip.play(80);
    }
    private void gameoverclip()
    {
        File soundeffect=new File("src/sample/Assets/gameover.mp3");
        AudioClip clip=new AudioClip(soundeffect.toURI().toString());
        clip.play(1);
    }
    private void colorswitchclip()
    {
        File soundeffect=new File("src/sample/Assets/colorswitchclip.mp3");
        AudioClip clip=new AudioClip(soundeffect.toURI().toString());
        clip.play(1);
    }
    private void starclip()
    {
        File soundeffect=new File("src/sample/Assets/starcollected.mp3");

        AudioClip clip=new AudioClip(soundeffect.toURI().toString());
        clip.play(1);
    }
    void start() {
        tim.pause();
        mainScene.setOnKeyReleased(k -> {
            String code = k.getCode().toString();
            if(code == "UP") {
                tim.play();
                //finger=null;
            }
            finger.setVisible(false);
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
                    clip();
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
        gameoverclip();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EndGamePage.fxml"));
            Parent sgRoot=loader.load();
            EndGamePageController endgamecontroller=loader.getController();
            endgamecontroller.setScore();
            Scene sgScene=new Scene(sgRoot);
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setScene(sgScene);
            window.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void pause() {
        tim.pause();
        for(Obstacle obs1: GamePlayPageController.obstacles) {
            obs1.stopRotate();
        }
    }

    public void resume() throws FileNotFoundException {

        tim.play();
       //gravity();
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
                starclip();
                GamePlayPageController.addPoint();
                s.disappear();
            }
        }
        for(colorSwitch cs: GamePlayPageController.colorSwitches) {
            if(ball.getBoundsInParent().intersects(cs.cs.getBoundsInParent()) && !cs.used) {
                cs.disappear();
                colorswitchclip();
            }
        }
    }
    void moveObstacles() throws FileNotFoundException {
        double delta = ball.getCenterY() - 360;
       // finger.setVisible(false);
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

    public void setWindow(Stage passtoball) {

    }
}
