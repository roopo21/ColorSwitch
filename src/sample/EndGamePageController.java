package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
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


import java.io.*;
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

    Stage bigStage;



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
    public void checkRevival(ActionEvent event) throws FileNotFoundException {
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        if(GamePlayPageController.getCurrScore()>0)
        {
            GamePlayPageController.ball.resetPosition();
            GamePlayPageController.ball.start();
            GamePlayPageController.ball.resume();
            GamePlayPageController.setScore(GamePlayPageController.getCurrScore()-1);
            window.close();

        }

        else{
            buzzerClip();
        }


    }
    private void buzzerClip()
    {
        File soundeffect=new File("src/sample/Assets/buzzer.mp3");
        AudioClip clip=new AudioClip(soundeffect.toURI().toString());
        clip.play(1);
    }

    public void doRestart(ActionEvent event) throws IOException {
//
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        MainPageController abcd=new MainPageController();
        abcd.changeScreenRestartgame(event,MainPageController.MainWindow);
        window.close();
    }

    public void backToMain(ActionEvent event) throws IOException {
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
       Scene sgScene=new Scene(sgRoot);
       MainPageController.MainWindow.setScene(sgScene);
       window.close();

    }

    private void setTopScore()
    {

    }

    public void setScore() {

        if (GamePlayPageController.getCurrScore()>GamePlayPageController.getTopScore())
        {
            GamePlayPageController.TopScore=GamePlayPageController.getCurrScore();
        }
        ScoreText.setText(" "+GamePlayPageController.getCurrScore());
        TopScoreText.setText(" "+GamePlayPageController.getTopScore());
        try{
            FileOutputStream fout=new FileOutputStream("highscore.txt");
            DataOutputStream dos= new DataOutputStream(fout);
            dos.writeInt(GamePlayPageController.getTopScore());
            dos.close();
            fout.close();
        }catch (Exception e){
        }



    }

}
