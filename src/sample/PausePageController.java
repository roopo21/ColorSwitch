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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PausePageController implements Initializable {
    static Stage bigStage;
    ball bigBall;
    @FXML
    private Button restart,endgame,settings,savegame,closebutton;
    public void changeScreenRemovesettings(ActionEvent event) throws IOException {
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

    }

    public void changeScreenRestart(ActionEvent event) throws IOException {

        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        MainPageController abcd=new MainPageController();
        abcd.changeScreenRestartgame(event,MainPageController.MainWindow);
        window.close();
    }

    public void changeScreenExit(ActionEvent event)throws IOException{
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
        bigStage.close();
    }


    public void changeScreenResume(ActionEvent event)throws IOException{
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
        bigBall.resume();
        System.out.println("dfsdfsd");
    }
    public void changeSaveGame(ActionEvent event)throws IOException{
        // code goes here for saving.

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setWindow(Stage window,ball Ball)
    {
        bigBall=Ball;
        bigStage=window;
        bigStage.setWidth(1280);
        bigStage.setHeight(720);
        //bigStage.setScene(mainScene);

    }
    public void saveGame(ActionEvent event) throws IOException, ClassNotFoundException {

        //Datatable datatable=new Datatable(GamePlayPageController.getCurrScore(),GamePlayPageController.stars,GamePlayPageController.colorSwitches,GamePlayPageController.obstacles);
        Main.Savedgames.add(GamePlayPageController.getCurrScore());
        Main.serialize();
    }
}
