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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PausePageController implements Initializable {
    Stage bigStage;
    @FXML
    private Button restart,endgame,settings,savegame,closebutton;
    public void changeScreenRemovesettings(ActionEvent event) throws IOException {
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

        //resume code goes here if needed the instance of gameplaypagecontroller can be passed along with the setwindow function below.

    }

    public void changeScreenRestart(ActionEvent event) throws IOException {
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("GamePlayPage.fxml"));
        Parent sgRoot=loader.load();
        Scene sgScene=new Scene(sgRoot);
        //Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        //System.out.println(window==null);
        GamePlayPageController controlla=loader.getController();
        controlla.setWindow(window);
        controlla.restartGame();



    }
    public void changeScreenExit(ActionEvent event)throws IOException{
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

        bigStage.close();

    }
    public void changeSaveGame(ActionEvent event)throws IOException{
        // code goes here for saving.

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setWindow(Stage window)
    {
        bigStage=window;
        bigStage.setWidth(1280);
        bigStage.setHeight(720);
        //bigStage.setScene(mainScene);

    }
}
