package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GamePlayPageController {
    public static ball ball;
    Button pause;
    public static ArrayList<Obstacle> obstacles;
    public static ArrayList<Star> stars;
    public static ArrayList<colorSwitch> colorSwitches;
    BorderPane pausePane=new BorderPane();
    public static int currScore = 0;
    public static int totalScore = 0;
    public static String[] colors;

    Image BackButton = new Image(new FileInputStream("src/sample/Assets/back.png"));
    Image finger = new Image(new FileInputStream("src/sample/Assets/finger.png"));
    Image plusOne = new Image(new FileInputStream("src/sample/Assets/+1.png"));
    Image PauseImage = new Image(new FileInputStream("src/sample/Assets/flatLight12.png"));
    //FileInputStream backbutton = new FileInputStream("src/sample/Assets/back.jpg");
    //Image BackButton = new Image(backbutton);
    @FXML
    public AnchorPane GamePlayRoot;
    public AnchorPane invisiRoot;

    public GamePlayPageController() throws FileNotFoundException {
    }

    @FXML
    private void initialize() throws FileNotFoundException {
        colors = new String[4];
        colors[0] = "0x7f03f6ff";
        colors[1] = "0x0ac3d1ff";
        colors[2] = "0xf8126bff";
        colors[3] = "0xfbd327ff";
        pause = new Button();
        Label Score=new Label("Score -"+ currScore);
        Score.setFont(new Font("Arial",24));

        pause.setPrefSize(80, 80);
        pause.setTranslateX(1210);
        pause.setTranslateY(0);
        //pause.setGraphic(new ImageView(BackButton));
        BackgroundImage backgroundImage = new BackgroundImage( new Image( new FileInputStream("src/sample/Assets/flatLight12.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        pause.setBackground(background);
        GamePlayRoot = new AnchorPane(pause,pausePane,Score);
        invisiRoot = new AnchorPane();



        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(720);
        Scene mainScene = new Scene(GamePlayRoot);
        stage.setScene(mainScene);
        stage.show();
        ball = new ball(GamePlayRoot, mainScene);
        createObstacles();
        GamePlayRoot.setBackground(new Background(new BackgroundFill(Paint.valueOf("#272727"),null,null)));
        //pause.setGraphic(new ImageView(BackButton));
        //ball.collision();

        pause.setOnAction(e -> {
            try {
                Parent sgRoot= FXMLLoader.load(getClass().getResource("PausePage.fxml"));
                Scene sgScene=new Scene(sgRoot);
                Stage window=new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setScene(sgScene);
                window.show();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        });


        //GamePlayRoot.getChildren().add(ball);
    }

    void createObstacles() throws FileNotFoundException {
        obstacles = new ArrayList<Obstacle>();
        stars = new ArrayList<Star>();
        colorSwitches = new ArrayList<colorSwitch>();
        circleObstacle circle = new circleObstacle(GamePlayRoot, invisiRoot);
        doublePlusObstacle doub = new doublePlusObstacle(GamePlayRoot, invisiRoot);
        rectangleObstacle rect = new rectangleObstacle(GamePlayRoot, invisiRoot);
        plusObstacle plus = new plusObstacle(GamePlayRoot, invisiRoot);
        obstacles.add(circle);
        obstacles.add(doub);
        obstacles.add(rect);
        obstacles.add(plus);
        firstCircle(circle);
    }

    static void spawnRandomObstacle() throws FileNotFoundException {
        Random rand = new Random();
        int sizeObstacles = obstacles.size();
        while(true) {
            int i = rand.nextInt(sizeObstacles);
            Obstacle obs = obstacles.get(i);
            if(obs.onScreen == false) {
                obs.appear();
                obs.onScreen = true;
                break;
            }
        }
    }
    void firstCircle(circleObstacle circle) throws FileNotFoundException {
        circle.group.setTranslateY(0);
        circle.onScreen = true;
        circle.appear();
    }



}
