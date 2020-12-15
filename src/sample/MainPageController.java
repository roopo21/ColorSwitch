package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainPageController implements Initializable {
    @FXML
    private AnchorPane mainPage;

    @FXML
    private Button newGame;
    @FXML
    private Button savedGames;
    @FXML
    private Button exitGame;
    @FXML
    private Button leaderboard;
    @FXML
    private Button settings;
    @FXML
    private BorderPane settingsPane;
    private AnchorPane circlePane;
    //circleObstacle mainPageCircle=new circleObstacle(circlePane);

    public void changeScreenNewGame(ActionEvent event) throws IOException {
        Parent sgRoot= FXMLLoader.load(getClass().getResource("GamePlayPage.fxml"));
        Scene sgScene=new Scene(sgRoot);
        //This line will get the stage information
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sgScene);
        window.close();
    }


    public void changeScreenSavedGame(ActionEvent event) throws IOException {
    Parent sgRoot= FXMLLoader.load(getClass().getResource("SavedPage.fxml"));
    Scene sgScene=new Scene(sgRoot);
    //This line will get the stage information
    Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(sgScene);
    window.show();
}

    public void changeScreenLeaderboard(ActionEvent event) throws IOException {
        Parent sgRoot= FXMLLoader.load(getClass().getResource("LeaderboardPage.fxml"));
        Scene sgScene=new Scene(sgRoot);
        //This line will get the stage information
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sgScene);
        window.show();
    }

    public void changeScreenExit(ActionEvent event) throws IOException {
        //Parent sgRoot= FXMLLoader.load(getClass().getResource("SavedPage.fxml"));
       // Scene sgScene=new Scene(sgRoot);
        //This line will get the stage information
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        //window.setScene(sgScene);
        window.close();
        //window.show();
    }

    public void changeScreenSettings(ActionEvent event) throws IOException {
        //Parent sgRoot= FXMLLoader.load(getClass().getResource("SavedPage.fxml"));
        // Scene sgScene=new Scene(sgRoot);
        //This line will get the stage information
        //Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        //window.setScene(sgScene);
        //window.close();
        //window.show();
        //System.out.println("done");
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        Pane view=FXMLLoader.load(getClass().getResource("SettingsPage.fxml"));
        //Scene sgScene=new Scene(view);
        //window.setScene(sgScene);
        settingsPane.setCenter(view);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //circleObstacle mainPageCircle=new circleObstacle(circlePane);

    }
}
