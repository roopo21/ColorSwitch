package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EndGamePageController implements Initializable {
    @FXML
    Button restart;
    @FXML
    Button back;
    @FXML
    Button revivalButton;
    @FXML
    AnchorPane EndgameRoot;
    @FXML
    Text finalScore,endGame;
    //set endGame text to Game ended
    @FXML
    Text reviveText,topscore;
    //set revivepossible to true or false depending on the stars.
    public int Score,TopScore;
    @FXML
    Text ScoreText,TopScoreText;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        finalScore.setText("SCORE");//score to be added here
        endGame.setText("GAME OVER");
        reviveText.setText("5 Keys needed to revive");
        topscore.setText("TOP SCORE");
        //finalScore.setText(0+" ");
        try {
            endGame.setFont(Font.loadFont(new FileInputStream("src/sample/Fonts/BT.otf"),35));
            finalScore.setFont(Font.loadFont(new FileInputStream("src/sample/Fonts/BT.otf"),40));
            reviveText.setFont(Font.loadFont(new FileInputStream("src/sample/Fonts/BT.otf"),20));
            topscore.setFont(Font.loadFont(new FileInputStream("src/sample/Fonts/BT.otf"),40));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




    }
    public void checkRevival()
    {

    }

    public void doRestart()
    {

    }

    public void backToMain()
    {

    }

    private void setTopScore()
    {

    }

    public void setScore(int currScore) {
        Score=currScore;
        if(Score>TopScore)
        {
            TopScore=Score;
        }
    }
}
