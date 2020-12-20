package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class SavedPageController implements Initializable {

@FXML
private Button back;
@FXML
private ListView gameStateList;
@FXML
private Button deleteProgress;
ObservableList<Integer> d= FXCollections.observableList(Main.Savedgames);



    public void changeScreenBacktoMain(ActionEvent event) throws IOException {
        Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene sgScene=new Scene(sgRoot);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sgScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameStateList.setItems(d);
    }

    public void deleteAllProgress(ActionEvent event) throws IOException {
       File file = new File("savedgames.txt");
       if(file.delete())
       {
           Main.Savedgames=new ArrayList<Integer>();
           Main.serialize();
           gameStateList.getItems().clear();
       }

    }

    public void handleMouseClick(MouseEvent event ) throws IOException {

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("GamePlayPage.fxml"));
        Parent sgRoot=fxmlLoader.load();
        Scene sgScene=new Scene(sgRoot);
        Object temp= gameStateList.getSelectionModel().getSelectedItem();
        Main.Savedgames.remove((Integer)temp);
        gameStateList.setItems(FXCollections.observableList(Main.Savedgames));
        GamePlayPageController controller = fxmlLoader.<GamePlayPageController>getController();
        controller.setWindow(MainPageController.MainWindow);
        controller.setScore((Integer)temp);

    }
}
