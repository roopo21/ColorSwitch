package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {
    public static MediaPlayer mediaPlayer;

    public static ArrayList<Integer> Savedgames=new ArrayList<Integer>();


    public static void serialize() throws IOException {
        ObjectOutputStream out=null;
        try {

            out=new ObjectOutputStream(new FileOutputStream("savedgames.txt"));
            out.writeObject(Savedgames);
            System.out.println(Savedgames.size()+" serialized");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        finally {
            out.close();
        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {

        try {

            FileInputStream fin=new FileInputStream("savedgames.txt");
            ObjectInputStream in=new ObjectInputStream(fin);
            Savedgames=(ArrayList<Integer>)in.readObject();
            in.close();
        } catch (FileNotFoundException | NullPointerException e){
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        deserialize();
        addMusic();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void addMusic() {
        Media sound = new Media(getClass().getResource("/sample/Assets/BGmusic.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.seconds(30));
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
