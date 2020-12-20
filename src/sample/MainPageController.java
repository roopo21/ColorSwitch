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

    public static Stage MainWindow;

    public void changeScreenNewGame(ActionEvent event) throws IOException {


        FXMLLoader loader= new FXMLLoader(getClass().getResource("GamePlayPage.fxml"));
        Parent sgRoot=loader.load();
        Scene sgScene=new Scene(sgRoot);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();

        GamePlayPageController controlla=loader.getController();
        controlla.setWindow(window);
        MainWindow=window;

    }


    public void changeScreenSavedGame(ActionEvent event) throws IOException {
    Parent sgRoot= FXMLLoader.load(getClass().getResource("SavedPage.fxml"));
    Scene sgScene=new Scene(sgRoot);

    Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(sgScene);
    window.show();
        MainWindow=window;
}

    public void changeScreenLeaderboard(ActionEvent event) throws IOException {
        Parent sgRoot= FXMLLoader.load(getClass().getResource("LeaderboardPage.fxml"));
        Scene sgScene=new Scene(sgRoot);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sgScene);
        window.show();
        MainWindow=window;
    }

    public void changeScreenExit(ActionEvent event) throws IOException {

        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();

        window.close();
        MainWindow=window;

    }

    public void changeScreenSettings(ActionEvent event) throws IOException {

        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        Pane view=FXMLLoader.load(getClass().getResource("SettingsPage.fxml"));

        settingsPane.setCenter(view);
        MainWindow=window;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void changeScreenRestartgame(ActionEvent event,Stage window) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("GamePlayPage.fxml"));
        Parent sgRoot=loader.load();
        Scene sgScene=new Scene(sgRoot);
        GamePlayPageController controlla=loader.getController();
        controlla.setWindow(window);
    }


}
