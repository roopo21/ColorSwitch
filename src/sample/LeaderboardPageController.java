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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardPageController implements Initializable {
    @FXML
    private Button Back;
    @FXML
    private Button reset;
    @FXML
    private Text Highscore;
    public void changeScreenBacktoMain(ActionEvent event) throws IOException {
        Parent sgRoot= FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene sgScene=new Scene(sgRoot);
        //This line will get the stage information
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sgScene);
        window.show();
    }
    public void setTextempty(ActionEvent event) throws IOException {
        try{
            FileOutputStream fout=new FileOutputStream("highscore.txt");
            //ObjectOutputStream out=new ObjectOutputStream(fout);
            DataOutputStream dos= new DataOutputStream(fout);
            dos.writeInt(0);
            //out.writeObject(savedGames);
            //fout.write();
            dos.close();

            System.out.println("hua");
            fout.close();

        }catch (Exception e){
            System.out.println("nhi hua");
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Highscore.setFont(Font.loadFont(new FileInputStream("src/sample/Fonts/BT.otf"),100));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            FileInputStream fin=new FileInputStream("highscore.txt");
            //ObjectOutputStream out=new ObjectOutputStream(fout);
            DataInputStream din= new DataInputStream(fin);
            int x= din.readInt();
            Highscore.setText(x+" ");
            System.out.println(x);
            //out.writeObject(savedGames);
            //fout.write();
            din.close();
            fin.close();

        }catch (Exception e){
            System.out.println("nhi hua");
            System.out.println(e);
        }


    }
}
