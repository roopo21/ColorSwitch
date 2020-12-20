package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

//public class Datatable implements Serializable {
//    private static final long serialVersionUID=42L;
//    private static int id=0;
//    private final int gameId;
//    //public static ball ball;
//    //Button pause;
//    public  ArrayList<Obstacle> obstacles;
//    public ArrayList<Star> stars;
//    public  ArrayList<colorSwitch> colorSwitches;
//    //BorderPane pausePane=new BorderPane();
//    public  int currScore ;
//    public static int TopScore ;
//    //Stage Window;
//    public static String[] colors;
//
//
//    public Datatable(int score) throws FileNotFoundException {
//        id++;
//        this.gameId=id;
//        //this.ball=new ball();
//        this.obstacles=new ArrayList<Obstacle>();
//        this.stars  =new ArrayList<Star>();
//        this.colorSwitches=new ArrayList<colorSwitch>();
//        this.currScore=score;
//    }
//
//    public void updateTable(ArrayList<Obstacle> Obstacles,ArrayList<Star> stars,ArrayList<colorSwitch> colorSwitches,int score)
//    {
//        this.obstacles=Obstacles;
//        this.stars  =stars;
//        this.colorSwitches=colorSwitches;
//        this.currScore=score;
//
//    }
//    public void saveGame()
//    {
//
//    }
//    public String toString()
//    {
//
//        return (currScore+" Score  of game"+stars.size()+" number of stars");
//    }
//
//
//}
public class Datatable implements Serializable{
    private int score;
    private ArrayList<Star> ListOfStars;
    private ArrayList<colorSwitch> ListOfColorSwitches;
    private ArrayList<Obstacle> ListOfObstacles;
    //private static final long serialVersionUID=42L;

    public Datatable( int score,ArrayList<Star> ListOfStars,ArrayList<colorSwitch> ListOfColorSwitches,ArrayList<Obstacle> ListOfObstacles)
    {
        this.score=score;
        this.ListOfColorSwitches=ListOfColorSwitches;
        this.ListOfObstacles=ListOfObstacles;
        this.ListOfStars=ListOfStars;

    }

    public ArrayList<colorSwitch> getListOfColorSwitches() {
        return ListOfColorSwitches;
    }

    public ArrayList<Obstacle> getListOfObstacles() {
        return ListOfObstacles;
    }

    public ArrayList<Star> getListOfStars() {
        return ListOfStars;
    }

    public int getScore() {
        return score;
    }

    public void setListOfColorSwitches(ArrayList<colorSwitch> listOfColorSwitches) {
        ListOfColorSwitches = listOfColorSwitches;
    }

    public void setListOfObstacles(ArrayList<Obstacle> listOfObstacles) {
        ListOfObstacles = listOfObstacles;
    }

    public void setListOfStars(ArrayList<Star> listOfStars) {
        ListOfStars = listOfStars;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Datatable{" +
                "score=" + score +
                '}';
    }
}
