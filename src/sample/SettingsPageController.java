package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class SettingsPageController implements Initializable {

@FXML
Button tick,cross,musicon,musicoff;
Slider difficulty;
    public void closeSettings(ActionEvent event) throws IOException {
        Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene sgScene=new Scene(sgRoot);
        //This line will get the stage information
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
       // Scene parentScene= ((Node)event.getSource()).getScene();
        window.setScene(sgScene);
        //window.setScene(Mai);
        window.show();
    }

    public void VolumeOff(ActionEvent event) throws IOException {
//        //Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//        //Scene sgScene=new Scene(sgRoot);
//        //This line will get the stage information
//        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
//        // Scene parentScene= ((Node)event.getSource()).getScene();
//        window.setScene(sgScene);
//        //window.setScene(Mai);
//        window.show();
    }

    public void VolumeOn(ActionEvent event) throws IOException {
//        //Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//        //Scene sgScene=new Scene(sgRoot);
//        //This line will get the stage information
//        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
//        // Scene parentScene= ((Node)event.getSource()).getScene();
//        window.setScene(sgScene);
//        //window.setScene(Mai);
//        window.show();
    }

    public void Difficulty(ActionEvent event) throws IOException {
//        //Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//        //Scene sgScene=new Scene(sgRoot);
//        //This line will get the stage information
//        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
//        // Scene parentScene= ((Node)event.getSource()).getScene();
//        window.setScene(sgScene);
//        //window.setScene(Mai);
//        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
