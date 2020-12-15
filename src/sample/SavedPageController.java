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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class SavedPageController implements Initializable {

@FXML
private Button back;

    public void changeScreenBacktoMain(ActionEvent event) throws IOException {
        Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene sgScene=new Scene(sgRoot);
        //This line will get the stage information
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sgScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
